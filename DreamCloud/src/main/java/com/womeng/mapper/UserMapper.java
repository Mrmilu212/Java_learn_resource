package com.womeng.mapper;

import com.womeng.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * userInfo Mapper 接口
 * </p>
 *
 * @author womeng
 * @since 2024-09-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where email = #{email}")
    User selectByEmail(String email);

    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);
}
