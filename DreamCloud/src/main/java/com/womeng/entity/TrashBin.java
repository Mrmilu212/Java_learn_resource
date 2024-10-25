package com.womeng.entity;

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
 * 回收站
 * </p>
 *
 * @author womeng
 * @since 2024-09-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("trash_bin")
@ApiModel(value = "TrashBin对象", description = "回收站")
public class TrashBin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件唯一标识")
    @TableId("file_id")
    private Long fileId;

    @ApiModelProperty("所属用户ID")
    private String userId;

    @ApiModelProperty("文件md5值")
    private String fileMd5;

    @ApiModelProperty("文件原始名称")
    private String fileName;

    @ApiModelProperty("完整父路径集合")
    private String filePath;

    @ApiModelProperty("目录下的文件集合")
    private String fileList;

    @ApiModelProperty("文件大小")
    private Long size;

    @ApiModelProperty("后缀名")
    private String suffix;

    @ApiModelProperty("是否目录")
    private Boolean isDir;

    @ApiModelProperty("文件删除时间")
    private LocalDateTime deleteAt;

    @ApiModelProperty("文件有效期")
    private LocalDateTime expire;
}
