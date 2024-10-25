package com.womeng.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FileNode implements Serializable {
    /**
     * 文件树
     */
    private Long id; // 文件或目录的ID
    private String name; // 文件或目录的名称
    private String url; // 文件url
    private Boolean isDir; // 是否为文件夹
    private String suffix; // 文件后缀
    private Integer depth; // 节点深度
    private String fileMd5; // 文件md5值
    private List<FileNode> children; // 子文件和子目录
}
