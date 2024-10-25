package com.womeng.service;

import com.womeng.entity.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.womeng.entity.Result;
import com.womeng.entity.dto.MultiFileDto;
import com.womeng.entity.dto.FileInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author womeng
 * @since 2024-09-20
 */
public interface IFileInfoService extends IService<FileInfo> {
    Result getFileList(FileInfoDto info);

    Result updateFileName(FileInfoDto info);

    Result delete(MultiFileDto info);

    Result move(FileInfoDto fileInfoDto);

    Result createFolder(FileInfoDto info);

    Result uploadFile(MultipartFile file, FileInfoDto info) throws IOException;

    Result download(MultiFileDto info);
}
