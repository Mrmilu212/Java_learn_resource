package com.womeng.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 删除文件请求体
 */
@Data
public class MultiFileDto {

    private String userId;

    private List<FileInfoDto> fileList;
}
