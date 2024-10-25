package com.womeng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.ObjectId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womeng.entity.*;
import com.womeng.entity.dto.*;
import com.womeng.entity.info.EmailVerifyInfo;
import com.womeng.entity.info.LoginInfo;
import com.womeng.entity.info.ResetPwdInfo;
import com.womeng.entity.info.SignUpInfo;
import com.womeng.mapper.FileInfoMapper;
import com.womeng.mapper.UserMapper;
import com.womeng.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.womeng.utils.JwtUtils;
import com.womeng.utils.RedisKeyUtils;
import com.womeng.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * user 服务实现类
 * </p>
 *
 * @author womeng
 * @since 2024-09-10
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final CaptchaService captchaService;

    private final EmailService emailService;

    private final UserMapper userMapper;

    private final RedisUtil redisUtil;

    private final RedisTemplate<String, Object> redisTemplate;

    private final FileInfoMapper fileInfoMapper;

    /**
     * 用户注册接口
     *
     * @param Info // 用户提交的注册相关信息
     * @return 统一响应结果
     */
    @Override
    public Result signUp(SignUpInfo Info) {
        // 读取用户提交的数据
        UserDTO userDTO = Info.getUserDTO();
        Captcha captcha = new Captcha();
        String key = RedisKeyUtils.initKey(RedisKeyUtils.REGISTER, userDTO.getEmail());

        captcha.setKey(key);
        captcha.setValue(Info.getCaptchaValue());

        // 检查验证码
        Result capRes = captchaService.verifyCaptcha(captcha);
        if (capRes.getCode() != 1) return capRes;
        // 检查用户信息

        String res = checkUserInfo(userDTO);
        if (res != null) {
            return Result.error(res);
        }

        // 获取用户根目录id
        Long rootPathId = createRootPath(userDTO);
        // 使用hutool包，设置唯一ID
        userDTO.setUserId(ObjectId.next().substring(0, 15));
        // 转换并保存数据
        User user = BeanUtil.copyProperties(userDTO, User.class);

        user.setRootPathId(rootPathId);
        // MP提供的方法
        save(user);

        return Result.success();
    }

    private Long createRootPath(UserDTO userDTO) {
        // 创建根目录
        FileInfo file = new FileInfo();
        file.setFileName(userDTO.getUsername());
        file.setUserId(userDTO.getUserId());
        fileInfoMapper.insert(file);
        // 获取根目录id
        return fileInfoMapper.getRootPathId(userDTO.getUserId(),userDTO.getUsername(),null);
    }

    /**
     * 默认登录接口
     *
     * @param info 用户登录信息
     * @return Result 统一响应结果
     */
    @Override
    public Result login(LoginInfo info) {
        User user = null;
        // 获取数据库中的用户数据
        if ( info.getEmail() != null)
            user = userMapper.selectByEmail(info.getEmail());
        if ( info.getUsername() != null)
            user = userMapper.selectByUsername(info.getUsername());

        if (user == null) {
            return Result.error("用户不存在");
        }
        // 检查密码 是否正确
        if (!user.getPassword().equals(info.getPassword())) {
            return Result.error("密码错误");
        }

        // 更新数据库中的最后登录时间
        lambdaUpdate()
                .set(User::getLastLoginTime, LocalDateTime.now())
                .eq(User::getUserId, user.getUserId())
                .update();

        // 生成JWT令牌
        String jwt = createJWT(user);

        return Result.success(jwt);
    }

   /* *//**
     * 通过邮箱登录
     *
     * @param emailVerifyInfo 邮箱登录的相关信息
     * @return 统一响应结果
     *//*
    @Override
    public Result loginByEmail(EmailVerifyInfo emailVerifyInfo) {
        // 检查验证码
        Result capRes = verifyLoginCaptcha(emailVerifyInfo);
        if (capRes.getCode() != 1) {
            return capRes;
        }

        String email = emailVerifyInfo.getEmail();

        if (email == null || email.equals("")) {
            return Result.error("请输入完整信息");
        }

        // 检查邮箱是否注册
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("email", email);
        User user = getOne(wrapper);
        if (user == null) {
            return Result.error("邮箱未注册");
        }

        // 生成JWT令牌
        String jwt = createJWT(user);

        return Result.success(jwt);
    }*/

    /**
     * 重置密码接口
     *
     * @param info 包含用户email和新密码
     * @return 统一响应结果
     */
    @Override
    public Result resetPwd(ResetPwdInfo info) {
        // 使用lambdaUpdate来更新密码
        lambdaUpdate()
                .set(User::getPassword, info.getNewPwd())
                .eq(User::getEmail, info.getEmail())
                .update();

        // 清空所有缓存，避免出现同时登录和重置密码的情况
        User user = userMapper.selectByEmail(info.getEmail());
        flushCache(user);
        return Result.success();
    }

    /**
     * 用户登出接口
     * @param request HTTP请求对象，用于获取token
     */
    @Override
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        // 从token中解析用户名
        Claims claims = JwtUtils.ParseJwt(token);
        // 获取Redis中该用户相关的key的唯一标识
        String username = claims.get("username").toString();
        String email = claims.get("email").toString();
        // 清理缓存
        flushCache(username);
        flushCache(email);
    }

    /**
     * 获取用户头像
     *
     * @param request HTTP请求
     * @return 用户头像url
     */
    @Override
    public String getAvatar(HttpServletRequest request) {
        User user = getUserInfo(request);
        // 返回用户头像url
        return user.getAvatar();
    }

    /**
     * 获取用户云盘空间信息
     *
     * @param request HTTP请求
     * @return 用户云盘空间信息
     */
    @Override
    public UserSpace getSpace(HttpServletRequest request) {
        // 借助token获取用户信息
        User user = getUserInfo(request);
        UserSpace userSpace = new UserSpace();
        userSpace.setUsedSpace(user.getUsedSpace());
        userSpace.setTotalSpace(user.getTotalSpace());
        return userSpace;
    }

    /**
     * 更新用户头像
     * @param userDTO 用户提交的信息
     * @return 统一响应结果
     */
    @Override
    public Result updateAvatar(UserDTO userDTO) {
        // 调用MP方法更新用户头URL
        lambdaUpdate()
                .set(userDTO.getAvatar()!=null,User::getAvatar,userDTO.getAvatar())
                .eq(User::getEmail,userDTO.getEmail())
                .update();
        return Result.success();
    }

    /**
     * 检查要重置的账户信息
     *
     * @param info 重置密码账户的信息
     * @return 如果返回code = 1 ，说明用户信息验证成功，存在于数据库中；否则验证失败
     */
    public Result preReset(UserDTO info) {
        String email = info.getEmail();
        String username = info.getUsername();

        if ((username == null || username.equals("")) && (email == null || email.equals(""))) {
            return Result.error("请输入正确的用户信息");
        }

        User user;
        // 邮箱登录方法的密码重置:检验邮箱是否注册
        if (email != null || !email.equals("")) {
             user = userMapper.selectByEmail(email);
            if (user == null) {
                return Result.error("邮箱未注册");
            }
            // 缓存用户数据
            cacheUserInfo(user);
            // 验证成功，发送验证码
            emailService.getResetCaptcha(email);
            return Result.success();
        } else {// 用户名登陆方法的密码重置：检验用户是否存在
            // 获取数据库中用户信息
             user = userMapper.selectByUsername(username);
            if (user == null) {
                return Result.error("用户名不存在");
            }
            // 缓存用户数据
            cacheUserInfo(user);
            // 用户存在，获取邮箱
            String emailInDB = user.getEmail();
            // 发送验证码到邮箱
            emailService.getResetCaptcha(emailInDB);
            return Result.success(emailInDB);
        }
    }

    /**
     * 检查重置密码的验证码
     * @param info 用户提交的验证信息
     * @return 统一响应结果
     */
    public Result verifyResetCaptcha(EmailVerifyInfo info) {
        Result result = verifyCaptcha(RedisKeyUtils.RESET, info.getEmail(), info.getCaptcha());
        // 校验通过，生成临时令牌，有效期10分钟
        if(result.getCode() == 1){
            Map<String, Object> claims = new HashMap<>();
            claims.put("email",info.getEmail());
            claims.put("captcha",info.getCaptcha());
            String jwt = JwtUtils.generateJwt(claims,60*60*10*1000L);
            // 生成临时token键值
            String key = RedisKeyUtils.initTempKey(info.getEmail(),info.getCaptcha());
            redisUtil.set(key, jwt, 60 * 10);
            result.setData(jwt);
        }

        return result;
    }

    /**
     * 检查用户的注册信息是否允许注册
     *
     * @param userDTO 用户的注册信息
     * @return String 检查结果
     */
    private String checkUserInfo(UserDTO userDTO) {
        // 检查信息是否完整
        if (userDTO.getPassword() == null || userDTO.getUsername() == null || userDTO.getEmail() == null) {
            return "请输入完整信息";
        }

        User user;

        // 检查用户名是否已经存在
        user = userMapper.selectByUsername(userDTO.getUsername());
        if (user != null) {
            // 优化：可以考虑将查询到的用户信息暂存到Redis中，防止用户重复使用相同用户名，造成资源浪费
            return "用户名已存在";
        }

        // 检查邮箱是否已经注册
        user = userMapper.selectByEmail(userDTO.getEmail());
        if (user != null) {
            return "邮箱已注册";
        }
        return null;
    }

    /**
     * 获取完整用户信息
     * @param request HTTP请求对象
     * @return 得到的用户信息
     */
    private User getUserInfo(HttpServletRequest request) {
        // 1.解析token获取用户名
        String token = request.getHeader("token");
        Claims claims = JwtUtils.ParseJwt(token);
        String email = claims.get("email").toString();
        // 获取键名
        String key = RedisKeyUtils.initKey(RedisKeyUtils.USER, email);
        // 2.查询Redis缓存
        User user = (User) redisUtil.get(key);

        if (user == null) { // 如果Redis中没有数据，查询数据库
            // 3.查询数据库数据
            user = getById((Serializable) claims.get("id"));
            // 查询到对象存储到Redis中
            redisUtil.set(key, user , 60*60*6);
        }

        return user;
    }

    /**
     * 清除指定字段的缓存
     *
     * @param pattern redis的键包含字段
     */
    private void flushCache(String pattern) {
        String[] keys = Objects.requireNonNull(redisTemplate
                .opsForValue()
                .getOperations()
                .keys("*" + pattern + "*"))
                .toArray(new String[0]);
        for (String key : keys) {
            redisTemplate.opsForValue().getOperations().delete(key);
        }
    }

    /**
     * flushCache(String pattern) 方法的重载
     *
     * @param user 用户信息
     */
    private void flushCache(User user) {
        flushCache(user.getUsername());
        flushCache(user.getEmail());
    }

    /**
     * 生成JWT令牌并存储
     *
     * @param user 用户信息
     * @return JWT令牌
     */
    private String createJWT(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getUserId());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        String jwt = JwtUtils.generateJwt(claims,60*60*6*1000L);

        // 将令牌和用户数据保存到Redis中
        String tokenKey = RedisKeyUtils.initKey(RedisKeyUtils.JWT, user.getEmail());
        String userKey = RedisKeyUtils.initKey(RedisKeyUtils.USER, user.getEmail());

        redisUtil.set(tokenKey, jwt, 60 * 60 * 6);
        redisUtil.set(userKey, user);

        return jwt;
    }

    /**
     * 缓存用户数据
     * @param user 用户数据
     */
    private void  cacheUserInfo(User user){
        if (user!=null) {
            String userKey = RedisKeyUtils.initKey(RedisKeyUtils.USER, user.getEmail());
            redisUtil.set(userKey, user);
        }
    }

    /**
     * 检查验证码
     * @param type 验证码类型
     * @param email 用户邮箱
     * @param captchaValue 验证码的值
     * @return 统一响应结果
     */
    private Result verifyCaptcha(String type , String email , String captchaValue){
        Captcha captcha = new Captcha();
        captcha.setKey(type + email);
        captcha.setValue(captchaValue);
        return captchaService.verifyCaptcha(captcha);
    }

    /**
     * @param info 封装用户提交的邮箱登录相关信息
     * @return 统一响应结果
     */
    private Result verifyLoginCaptcha(EmailVerifyInfo info) {
        return verifyCaptcha(RedisKeyUtils.LOGIN_EMAIL,info.getEmail(),info.getCaptcha());
    }

}
