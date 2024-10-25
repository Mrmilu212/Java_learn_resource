package com.womeng.service;

import com.womeng.entity.Result;
import com.womeng.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.womeng.entity.UserSpace;
import com.womeng.entity.dto.UserDTO;
import com.womeng.entity.info.EmailVerifyInfo;
import com.womeng.entity.info.LoginInfo;
import com.womeng.entity.info.ResetPwdInfo;
import com.womeng.entity.info.SignUpInfo;
import jakarta.servlet.http.HttpServletRequest;

/**
 * <p>
 * user 服务层接口
 * </p>
 *
 * @author womeng
 * @since 2024-09-10
 */
public interface IUserService extends IService<User> {

    Result signUp(SignUpInfo Info);

    //Result loginByEmail(EmailVerifyInfo emailVerifyInfo);

    Result login(LoginInfo user);

    Result resetPwd(ResetPwdInfo info);

    void logout(HttpServletRequest request);

    String getAvatar(HttpServletRequest request);

    UserSpace getSpace(HttpServletRequest request);

    Result updateAvatar(UserDTO userDTO);
}
