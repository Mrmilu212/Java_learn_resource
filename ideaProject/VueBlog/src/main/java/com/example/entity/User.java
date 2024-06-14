package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 麋鹿
 * @since 2024-06-01
 */
@Getter
@Setter
@TableName("m_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("avatar")
    private String avatar;

    @TableField("email")
    private String email;

    @TableField("password")
    private String password;

    @TableField("status")
    private Integer status;

    @TableField("created")
    private LocalDateTime created;

    @TableField("last_login")
    private LocalDateTime lastLogin;
}
