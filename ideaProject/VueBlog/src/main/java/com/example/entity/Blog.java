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
@TableName("m_blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("content")
    private String content;

    @TableField("created")
    private LocalDateTime created;

    @TableField("status")
    private Byte status;
}
