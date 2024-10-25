package com.womeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装用户的存储空间
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSpace {

    private Long usedSpace;

    private Long totalSpace;
}
