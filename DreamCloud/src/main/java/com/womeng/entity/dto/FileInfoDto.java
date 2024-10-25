package com.womeng.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件请求体
 */
@Data
public class FileInfoDto {

    private Long fileId;

    private String userId;

    private String fileName;

    private Boolean isDir;

    private Long targetFolderId;

    private String fileMd5;

    private Long size;

    private String suffix;
}
