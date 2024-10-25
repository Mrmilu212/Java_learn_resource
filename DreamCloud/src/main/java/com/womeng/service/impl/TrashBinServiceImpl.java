package com.womeng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.womeng.entity.FileInfo;
import com.womeng.entity.Result;
import com.womeng.entity.TrashBin;
import com.womeng.entity.dto.FileInfoDto;
import com.womeng.entity.dto.MultiFileDto;
import com.womeng.mapper.FileInfoMapper;
import com.womeng.mapper.TrashBinMapper;
import com.womeng.service.ITrashBinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 回收站 服务实现类
 * </p>
 *
 * @author womeng
 * @since 2024-09-20
 */
@Service
public class TrashBinServiceImpl extends ServiceImpl<TrashBinMapper, TrashBin> implements ITrashBinService {

    // TODO: 重要的操作需要添加，用户权限校验功能

    @Autowired
    private TrashBinMapper trashBinMapper;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * 查看回收站记录
     *
     * @param info 包含用户id的请求体
     * @return 统一响应结果
     */
    @Override
    public Result getTrashBinList(FileInfoDto info) {
        // 创建查询条件
        QueryWrapper<TrashBin> wrapper = new QueryWrapper<TrashBin>()
                .eq("user_id", info.getUserId())
                .orderByDesc("delete_at");
        // 查询对应数据
        List<TrashBin> list = trashBinMapper.selectList(wrapper);
        return Result.success(list);
    }

    /**
     * 还原文件
     *
     * @param info 包含文件和目录的两个集合
     * @return 统一响应结果
     */
    @Transactional(rollbackFor = Exception.class) // 所有异常都回滚
    @Override
    public Result recover(MultiFileDto info) {
        List<FileInfoDto> fileList = info.getFileList();
        if (fileList != null) {
            fileList.forEach(file -> {
                Long id = file.getFileId();
                // 查询回收站，获取记录
                TrashBin result = trashBinMapper.selectById(id);
                if (result == null || !result.getUserId().equals(info.getUserId())){
                    return; // 仅退出当前lambda表达式，循环会继续
                }
                if (result.getIsDir()) {// 仅操作存在的文件
                    recoverFolder(result);
                }else {
                    recoverFile(result);
                }
            });
        }
        // TODO 判断是否存在同类型同名状态正常的文件，有则在还原文件的文件名后添加 _rec后缀
        return Result.success();
    }

    // TODO: 清理回收站和还原文件对文件和目录的区分操作大同小异，可以将两个操作合并

    @Transactional
    @Override
    public Result clean(MultiFileDto info) {
        List<FileInfoDto> fileList = info.getFileList();
        HashMap<String,List<FileInfo>> map = new HashMap<>();
        if (fileList != null) {
            fileList.forEach(file -> {
                Long id = file.getFileId();
                // 查询回收站，获取记录
                TrashBin result = trashBinMapper.selectById(id);
                // 仅操作存在的文件
                if (result == null || !result.getUserId().equals(info.getUserId())){
                    return; // 仅退出当前lambda表达式，循环会继续
                }
                if (result.getIsDir()) {
                    cleanFolder(result,map);
                }else {
                    cleanFile(result,map);
                }
            });
        }

        // TODO: 检查是否还有正在使用的md5值（文件表，回收站表），将其从hashMap中移除
        //  根据MD5值获取文件url
        //  调用阿里云OSS删除文件源
        //  将对应数据记录的url设置为null
        return Result.success();
    }

    private void cleanFolder(TrashBin info, HashMap<String,List<FileInfo>> map) {
        // 获取目录下的文件md5值
        List<FileInfo> list = JSONObject.parseArray(info.getFileList(), FileInfo.class);
        list.forEach(file -> {
            getMD5AndUpdateStatus(file,map);
        });
        // 将文件表中对应数据的is_deleted字段设置为1
        UpdateWrapper<FileInfo> wrapper = new UpdateWrapper<FileInfo>()
                .set("is_deleted", true)
                .eq("id", info.getFileId());
        fileInfoMapper.update(wrapper);
        // 清除回收站记录
        deleteFromTrashBin(info.getFileId());
    }

    /**
     * 清除非目录文件
     * @param info 回收站文件记录的完整信息
     * @param map 以文件md5为键，共用这个md5的文件集合为值
     */
    private void cleanFile(TrashBin info, HashMap<String,List<FileInfo>> map) {
        // 转换为FileInfo类型
        FileInfo file = BeanUtil.copyProperties(info, FileInfo.class);
        file.setId(info.getFileId());
        getMD5AndUpdateStatus(file, map);
        // 清除回收站对应记录
        deleteFromTrashBin(info.getFileId());
    }

    /**
     * 获取文件的md5值并更新文件的状态
     * @param info 目标文件的完整信息
     * @param map 以文件md5为键，共用这个md5的文件集合为值
     */
    private void getMD5AndUpdateStatus(FileInfo info, HashMap<String, List<FileInfo>> map) {
        // 获取文件md5值
        String md5 = info.getFileMd5();
        // 以md5为键，FileInfo为值添加到map中
        // 将对应文件数据也添加到map集合中
        List<FileInfo> list = map.get(md5);
        if (list == null ){
            list = new ArrayList<>();
            list.add(info);
            map.put(md5,list);
        }else list.add(info);

        // 将文件表中对应数据的is_deleted字段设置为1
        UpdateWrapper<FileInfo> wrapper = new UpdateWrapper<FileInfo>()
                .set("is_deleted", true)
                .eq("id", info.getId());
        fileInfoMapper.update(wrapper);
    }

    /**
     * 还原目录
     * @param info 包含文件id和父级路径的回收站文件信息
     */
    private void recoverFolder(TrashBin info) {
        // 还原父级路径
        recoverPath(info);
        // 获取目录下同该目录一起被删除的文件
        List<FileInfo> list = JSONObject.parseArray(info.getFileList(),FileInfo.class);
        list.forEach(file -> {
            Long id = file.getId();
            updateStatus(id);
        });

        // 删除回收站记录
        deleteFromTrashBin(info.getFileId());
    }

    /**
     * 还原文件
     *
     * @param info 包含文件id和父级路径的回收站文件信息
     */
    private void recoverFile(TrashBin info) {
        // 还原父级路径
        recoverPath(info);
        // 还原文件: 修改文件trash_bin字段为 0
        Long id = info.getFileId();
        updateStatus(id);
        // 删除回收站表对应数据
        deleteFromTrashBin(id);
    }

    /**
     * 删除回收站记录
     *
     * @param id 回收站文件id
     */
    private void deleteFromTrashBin(Long id) {
        QueryWrapper<TrashBin> wrapper = new QueryWrapper<TrashBin>()
                .eq("file_id", id);
        trashBinMapper.delete(wrapper);
    }

    /**
     * 更新文件表中对应状态
     *
     * @param id 文件id
     */
    private void updateStatus(Long id) {
        UpdateWrapper<FileInfo> wrapper = new UpdateWrapper<FileInfo>()
                .set("trash_bin", false)
                .eq("id", id);
        fileInfoMapper.update(wrapper);
    }

    /**
     * 还原父级路径
     *
     * @param info 包含父级路径的回收站文件信息
     */
    private void recoverPath(TrashBin info) {
        String filePath = info.getFilePath();
        List<FileInfo> list = JSONObject.parseArray(filePath, FileInfo.class);
        // 还原父级路径：有则更新状态，无则插入数据
        fileInfoMapper.insertOrUpdate(list);
    }

    // TODO:需要一个定时检查回收站的功能以定期清理过期数据

}
