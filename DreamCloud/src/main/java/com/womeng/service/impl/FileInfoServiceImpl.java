package com.womeng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.womeng.entity.FileInfo;
import com.womeng.entity.FileNode;
import com.womeng.entity.Result;
import com.womeng.entity.TrashBin;
import com.womeng.entity.dto.DownloadDto;
import com.womeng.entity.dto.MultiFileDto;
import com.womeng.entity.dto.FileInfoDto;
import com.womeng.mapper.FileInfoMapper;
import com.womeng.mapper.TrashBinMapper;
import com.womeng.properties.AliOSSProperties;
import com.womeng.service.IFileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.womeng.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author womeng
 * @since 2024-09-20
 */
@Slf4j
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements IFileInfoService {

    // TODO 实现用户根目录保护：即用户根目录不可被用户删除

    // TODO 合并数据库用户id和parent_id索引

    // TODO 手动抛出异常，统一处理相同类型的错误

    // TODO 根据文件大小判断是否需要验证码

    // TODO 优化文件压缩逻辑

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private TrashBinMapper trashBinMapper;
    // 注入阿里云oss工具类
    @Autowired
    private AliOSSUtils aliOSSUtils;
    // 注入阿里云相关配置信息
    @Autowired
    private AliOSSProperties aliOSSProperties;

    @Override
    public Result getFileList(FileInfoDto info) {
        FileInfo fileInfo = fileInfoMapper.selectById(info.getFileId());
        if (fileInfo == null) {
            return Result.error("文件夹不存在");
        }

        if (!fileInfo.getIsDir()) {
            return Result.error("文件类型不匹配");
        }

        if (!fileInfo.getUserId().equals(info.getUserId())) {
            return Result.error("没有权限查看该目录");
        }

        // 查询数据库数据
        List<FileInfo> list = fileInfoMapper.selectNextList(info.getFileId());

        return Result.success(list);
    }

    @Override
    public Result updateFileName(FileInfoDto info) {
        FileInfo fileInfo = fileInfoMapper.selectById(info.getFileId());
        if (fileInfo == null) {
            return Result.error("文件不存在");
        }
        if (!fileInfo.getUserId().equals(info.getUserId())) {
            return Result.error("没有权限操作该文件");
        }
        // TODO: 还需实现文件名文件类型修改（或只改变预览方式）
        // 修改原始文件名
        String newName = info.getFileName();
        String newFileName = info.getFileName();
        // 调用mapper接口实现修改
        // 创建更新条件
        UpdateWrapper<FileInfo> wrapper = new UpdateWrapper<FileInfo>()
                .set("name", newName)
                .set("file_name", newFileName)
                .eq("id", info.getFileId());
        // 更新
        fileInfoMapper.update(wrapper);

        return Result.success();
    }

    /**
     * 删除文件接口
     *
     * @param info 包含文件集合的请求信息
     * @return 统一响应结果
     */
    @Transactional(rollbackFor = Exception.class) // 所有异常都回滚
    @Override
    public Result delete(MultiFileDto info) {

        List<FileInfoDto> fileList = info.getFileList();
        if (fileList != null) {
            fileList.forEach(file -> {
                Long id = file.getFileId();
                // 查询文件表
                FileInfo result = fileInfoMapper.selectById(id);
                // 仅允许操作当前登录用户有权限的文件
                if (result == null || !result.getUserId().equals(info.getUserId())) {
                    return; // 仅退出当前lambda表达式，循环会继续
                }
                if (result.getIsDir() && !result.getTrashBin()) {// 仅操作未删除的文件
                    deleteFolder(result);
                } else if (!result.getTrashBin()) {
                    deleteFile(result);
                }
            });
        }
        return Result.success();
    }

    /**
     * 移动文件夹
     *
     * @param info 包含源文件和目标文件id的请求信息
     * @return 统一响应结果
     */
    @Override
    public Result move(FileInfoDto info) {
        FileInfo file = fileInfoMapper.selectById(info.getFileId());
        FileInfo target = fileInfoMapper.selectById(info.getTargetFolderId());
        // 判断文件是否存在
        if (file == null || target == null) {
            return Result.error("文件或目标目录不存在");
        }
        // 判断是否有操作权限
        if (!file.getUserId().equals(info.getUserId())) {
            return Result.error("没有权限操作");
        }
        if (!target.getUserId().equals(info.getUserId())) {
            return Result.error("没有权限操作");
        }
        // 将文件的parent_id修改为接收到的targetFolderId
        file.setParentId(target.getId());
        // 更新文件信息
        fileInfoMapper.insertOrUpdate(file);

        return Result.success();
    }

    @Override
    public Result createFolder(FileInfoDto info) {
        FileInfo target = fileInfoMapper.selectById(info.getTargetFolderId());
        if (target == null) {
            return Result.error("目标目录不存在");
        }
        if (!target.getUserId().equals(info.getUserId())) {
            return Result.error("没有权限操作该目录");
        }
        // 查询目标目录下是否存在同名目录
        QueryWrapper<FileInfo> wrapper = new QueryWrapper<FileInfo>()
                .eq("name", info.getFileName())
                .eq("is_dir", true)
                .eq("parent_id", info.getTargetFolderId());
        List<FileInfo> list = fileInfoMapper.selectList(wrapper);

        Result result = new Result();
        if (list != null) {
            list.forEach(file -> {
                if (file.getName().equals(info.getFileName())) {
                    result.setCode(0);
                    result.setMsg("当前目录下存在同名目录");
                    result.setData(null);
                    return;
                }
                // 判断文件是否处于删除状态
                if (file.getTrashBin()) {
                    // 启用已删除目录
                    lambdaUpdate()
                            .set(FileInfo::getTrashBin, false)
                            .set(FileInfo::getIsDeleted, false)
                            .eq(FileInfo::getId, file.getId());
                    result.setCode(1);
                    result.setMsg("success");
                    result.setData(null);
                }
            });
        }
        if (result.getMsg() != null) {
            return result;
        }

        FileInfo file = new FileInfo();
        file.setName(info.getFileName());
        file.setFileName(info.getFileName());

        fileInfoMapper.insert(file);
        return Result.success();
    }

    @Transactional
    @Override
    public Result uploadFile(MultipartFile file, FileInfoDto info) throws IOException {
        // 判断用户是否有权限操作目标文件夹
        FileInfo target = fileInfoMapper.selectById(info.getTargetFolderId());
        if (!target.getUserId().equals(info.getUserId())) {
            return Result.error("没有权限在此文件夹上传文件");
        }
        if (info.getFileName() == null) {
            return Result.error("文件名不能为空");
        }
        // 创建新的文件对象
        FileInfo fileInfo = BeanUtil.copyProperties(info, FileInfo.class);
        fileInfo.setParentId(info.getTargetFolderId());
        fileInfo.setName(info.getFileName());
        // 查询数据库中是否存在相同md5的文件
        QueryWrapper<FileInfo> wrapper = new QueryWrapper<FileInfo>()
                .eq("file_md5", info.getFileMd5());
        List<FileInfo> list = fileInfoMapper.selectList(wrapper);

        // 判断同目录下是否有同名文件
        QueryWrapper<FileInfo> wrapper1 = new QueryWrapper<FileInfo>()
                .eq("file_name", info.getFileName())
                .eq("parent_id", info.getTargetFolderId());
        Long count = fileInfoMapper.selectCount(wrapper1);
        if (count != 0) {
            return Result.error("目录下存在同名文件");
        }
        // 如果存在正在使用相同md5的文件
        if (!list.isEmpty()) {
            // 将数据库中存在的文件url复制到当前上传文件
            fileInfo.setUrl(list.get(0).getUrl());
        } else {
            // 不存在数据则将文件上传到阿里云OSS
            String url = aliOSSUtils.upload(file, info.getFileMd5(), info.getSuffix());
            fileInfo.setUrl(url);
        }
        fileInfoMapper.insert(fileInfo);
        return Result.success();
    }

    @Override
    public Result download(MultiFileDto info) {

        List<FileInfoDto> list = info.getFileList();
        // 获取要下载的文件url集合
        List<String> urls = new ArrayList<>();
        List<byte[]> zips = new ArrayList<>();
        list.forEach(file -> {
            // 查询数据中是否存在对应数据
            FileInfo fileInfo = fileInfoMapper.selectById(file.getFileId());
            if (fileInfo == null || !fileInfo.getUserId().equals(info.getUserId())) {
                log.info("文件 {} 下载失败", fileInfo == null ? "" : fileInfo.getId());
                return;
            }
            if (!fileInfo.getIsDir()) {
                // 直接将文件的url添加到集合中
                urls.add(fileInfo.getUrl());
            } else {
                try {
                    byte[] bytes = downloadFolder(fileInfo);
                    zips.add(bytes);
                } catch (IOException e) {
                    log.info("文件压缩异常");
                    e.printStackTrace();
                }
            }
        });

        DownloadDto downloadDto = new DownloadDto();
        downloadDto.setUrls(urls);
        downloadDto.setZips(zips);
        return Result.success(downloadDto);
    }

    private byte[] downloadFolder(FileInfo file) throws IOException {
        // 获取以该目录为根节点的文件树
        FileNode fileTree = getFileTree(file, null);
        // 根据文件树创建压缩包
        return createZipFromFileTree(fileTree);
    }

    /**
     * @param source 树根节点文件
     * @param depth  树的深度
     * @return 完整的文件树
     */
    private FileNode getFileTree(FileInfo source, Integer depth) {
        // 查询当前目录下的所有文件和子目录
        List<FileInfo> files = fileInfoMapper.selectNextList(source.getId());
        // 将父目录转换为文件节点
        FileNode parentNode = BeanUtil.copyProperties(source, FileNode.class);
        // 设置节点深度
        parentNode.setDepth(Objects.requireNonNullElse(depth, 0));

        ArrayList<FileNode> children = new ArrayList<>();
        files.forEach(file -> {
            FileNode childNode;
            if (file.getIsDir()) {
                // 如果是目录，进一步搜索子树
                childNode = getFileTree(file, parentNode.getDepth() + 1);
            } else {
                // 否则直接转换为 叶子节点
                childNode = BeanUtil.copyProperties(file, FileNode.class);
                childNode.setDepth(parentNode.getDepth() + 1);
            }
            children.add(childNode);
        });
        parentNode.setChildren(children);
        return parentNode;
    }

    private byte[] createZipFromFileTree(FileNode rootNode) throws IOException {
        // 获取阿里云OSS参数
        String endpoint = aliOSSProperties.getEndpoint();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName = aliOSSProperties.getBucketName();

        // 从 OSS 获取文件输入流
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        Set<String> addedEntries = new HashSet<>();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 创建输出流
        try (ZipOutputStream zipOut = new ZipOutputStream(byteArrayOutputStream)) {
            addFileNodeToZip(rootNode, zipOut, "", ossClient, bucketName,addedEntries); // 添加文件和目录到压缩包
        }
        // 关闭 OSSClient
        ossClient.shutdown();
        return byteArrayOutputStream.toByteArray(); // 返回压缩包的字节数组
    }

    private void addFileNodeToZip(FileNode node, ZipOutputStream zipOut, String parentPath, OSS ossClient, String bucketName, Set<String> addedEntries) throws IOException {
        // 拼接当前路径
        String currentPath = parentPath + node.getName() + (node.getIsDir() ? "/" : node.getSuffix());

        // 检查是否已经添加过该条目
        if (addedEntries.contains(currentPath)) {
            log.info("跳过重复路径{} ",currentPath);
            return;
        }

        // 如果当前节点是目录
        if (node.getIsDir()) {
            zipOut.putNextEntry(new ZipEntry(currentPath));
            zipOut.closeEntry();
            log.info("目录{}压缩成功", node.getName());
            addedEntries.add(currentPath);
        } else {
            // 如果是文件，将阿里云oss的文件直接下载到压缩包
            String ossFilePath = node.getFileMd5() + node.getSuffix(); // 获取 OSS 文件路径
            if (node.getUrl() != null) {
                try (InputStream inputStream = ossClient.getObject(bucketName, ossFilePath).getObjectContent()) {
                    zipOut.putNextEntry(new ZipEntry(currentPath)); // 添加文件条目
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        zipOut.write(buffer, 0, length); // 将输入流写入压缩包
                    }
                    zipOut.closeEntry(); // 关闭文件条目
                    addedEntries.add(currentPath);
                }
            }
            log.info("文件{}压缩成功", node.getName());
        }
        // 递归处理子节点
        List<FileNode> children = node.getChildren();

        if (children != null) {
            children.forEach(child -> {
                try {
                    // 为子节点构建新的父路径
                    String childParentPath = currentPath.endsWith("/") ? currentPath : currentPath + "/";
                    addFileNodeToZip(child, zipOut, childParentPath, ossClient, bucketName,addedEntries);
                } catch (IOException e) {
                    log.info("文件{}压缩异常", child.getId());
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 删除目录
     *
     * @param info 要删除的目录信息
     */
    private void deleteFolder(FileInfo info) {

        // 获取该目录下的所有文件
        List<FileInfo> list = fileInfoMapper.selectByParentId(info.getId());
        // 修改文件状态
        List<FileInfo> fileList = new ArrayList<>();
        list.forEach(file -> {
            if (!file.getTrashBin()) { // 只操作未删除的文件
                updateStatus(file);
                fileList.add(file);
            }
        });
        // 修改文件夹状态文件夹
        updateStatus(info);
        // 将文件夹添加到回收站
        addToTrashBin(info, fileList);
    }

    /**
     * 删除文件
     *
     * @param info 要删除的文件信息
     */
    private void deleteFile(FileInfo info) {
        updateStatus(info);
        addToTrashBin(info, null);
    }

    /**
     * 更新数据库中文件的trash_bin字段
     *
     * @param info 包含要删除的文件id的文件信息
     */
    private void updateStatus(FileInfo info) {
        // 创建更新条件
        UpdateWrapper<FileInfo> wrapper = new UpdateWrapper<FileInfo>()
                .set("trash_bin", 1)// 将文件表中trash_bin字段设置为1
                .eq("id", info.getId());
        // 更新 文件表
        fileInfoMapper.update(wrapper);
    }

    /**
     * 将从文件表中删除的文件的信息添加到回收站表中
     *
     * @param info 删除的文件的信息
     */
    private void addToTrashBin(FileInfo info, List<FileInfo> list) {
        // 将文件信息添加到回收站中
        // 将属性对应文件复制到回收站文件中
        TrashBin trashBin = BeanUtil.copyProperties(info, TrashBin.class);
        // 复制文件id
        trashBin.setFileId(info.getId());
        // 获取文件完整路径
        List<FileInfo> path = fileInfoMapper.getFilePath(info.getId());
        // 将集合转换为JSON类型
        String pathJson = JSON.toJSONString(path);
        // 将设置文件路径
        trashBin.setFilePath(pathJson);
        // 将随目录被同时删除的文件添加到回收站文件记录中
        String fileList = JSON.toJSONString(list);
        trashBin.setFileList(fileList);
        // 设置文件有效期为当前时间的30天后
        LocalDateTime expire = LocalDateTime.now().plus(30, ChronoUnit.DAYS);
        trashBin.setExpire(expire);

        trashBinMapper.insert(trashBin);
    }
}
