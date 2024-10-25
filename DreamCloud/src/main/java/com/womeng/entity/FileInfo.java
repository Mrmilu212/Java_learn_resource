package com.womeng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author womeng
 * @since 2024-09-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("file_info")
@ApiModel(value = "FileInfo对象", description = "文件表")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件唯一标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("文件md5值")
    private String fileMd5;

    @ApiModelProperty("所属用户ID")
    private String userId;

    @ApiModelProperty("文件原始名称")
    private String name;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件存储url")
    private String url;

    @ApiModelProperty("文件大小")
    private Long size;

    @ApiModelProperty("后缀名")
    private String suffix;

    @ApiModelProperty("是否图片")
    private Boolean isImg;

    @ApiModelProperty("是否目录")
    private Boolean isDir;

    @ApiModelProperty("父级文件夹")
    private Long parentId;

    @ApiModelProperty("文件修改(上传)时间")
    private LocalDateTime updateAt;

    @ApiModelProperty("是否在回收站")
    private Boolean trashBin;

    @ApiModelProperty("是否已经删除")
    private Boolean isDeleted;
}
