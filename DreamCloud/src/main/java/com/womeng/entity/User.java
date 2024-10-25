package com.womeng.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * userInfo
 * </p>
 *
 * @author womeng
 * @since 2024-09-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User对象", description = "userInfo")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId("user_id")
    private String userId;

    @ApiModelProperty("昵称")
    private String username;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户头像")
    private String Avatar;

    @ApiModelProperty("密码")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty("加入时间")
    private LocalDateTime joinTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty("最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("0:禁用 1:启用")
    private Byte status;

    @ApiModelProperty("使用空间单位byte")
    private Long usedSpace;

    @ApiModelProperty("总空间")
    private Long totalSpace;

    @ApiModelProperty("用户根目录id")
    private Long rootPathId;
}
