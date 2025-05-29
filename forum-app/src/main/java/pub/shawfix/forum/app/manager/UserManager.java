package pub.shawfix.forum.app.manager;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import pub.shawfix.forum.api.request.user.UserEmailLoginRequest;
import pub.shawfix.forum.api.request.user.UserRegisterRequest;
import pub.shawfix.forum.api.request.user.UserTokenLogoutRequest;
import pub.shawfix.forum.api.request.user.UserUpdateInfoRequest;
import pub.shawfix.forum.app.support.IsLogin;
import pub.shawfix.forum.app.support.LoginUserContext;
import pub.shawfix.forum.app.transfer.UserTransfer;
import pub.shawfix.forum.common.enums.CacheBizTypeEn;
import pub.shawfix.forum.common.enums.ErrorCodeEn;
import pub.shawfix.forum.common.enums.UserStateEn;
import pub.shawfix.forum.common.support.CheckUtil;
import pub.shawfix.forum.common.support.EventBus;
import pub.shawfix.forum.common.support.StringUtil;
import pub.shawfix.forum.domain.entity.OptLog;
import pub.shawfix.forum.domain.entity.User;
import pub.shawfix.forum.domain.repository.OptLogRepository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author shawfix
 * @create 2025/5/28 10:30
 * @desc
 **/
@Component
public class UserManager extends AbstractLoginManager {

    @Resource
    private OptLogRepository optLogRepository;

    /**
     * 邮箱 + 密码 登录
     * @param request
     * @return
     */
    public String emailRequestLogin(UserEmailLoginRequest request) {
        // 判断邮箱是否存在
        User user = userRepository.getByEmail(request.getEmail());
        CheckUtil.isEmpty(user, ErrorCodeEn.USER_NOT_EXIST);
        CheckUtil.isTrue(UserStateEn.DISABLE.equals(user.getState()), ErrorCodeEn.USER_STATE_IS_DISABLE);

        // 判断登录密码是否正确
        CheckUtil.isFalse(StringUtil.md5UserPassword(request.getPassword()).equals(user.getPassword()), ErrorCodeEn.USER_LOGIN_PWD_ERROR);

        // 最后登录时间
        user.setLastLoginTime(new Date());
        userRepository.update(user);

        return login(user, request);
    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @Transactional
    public String register(UserRegisterRequest request) {
        // 判断邮箱是否已经被注册
        User user = userRepository.getByEmail(request.getEmail());
        CheckUtil.isNotEmpty(user, ErrorCodeEn.USER_REGISTER_EMAIL_IS_EXIST);

        User registerUser = UserTransfer.toUser(request);

        // 保存注册用户
        userRepository.save(registerUser);

        // 触发保存操作日志事件
        // TODO: EventBus的实现
        EventBus.emit(EventBus.Topic.USER_REGISTER, registerUser);

        return login(registerUser, request);
    }


    @IsLogin
    @Transactional
    public void updateInfo(UserUpdateInfoRequest request) {
        User loginUser = LoginUserContext.getUser();
        User user = userRepository.getByEmail(request.getEmail());
        if (!ObjectUtils.isEmpty(user)) {
            CheckUtil.isFalse(user.getId().equals(loginUser.getId()), ErrorCodeEn.USER_REGISTER_EMAIL_IS_EXIST);
        }

        User updateUser = UserTransfer.toUser(loginUser, request);

        // 更新缓存中登录用户信息
        updateCacheUser(updateUser);
        userRepository.update(updateUser);
    }

    /**
     * 更新用户头像
     * @param linkFilenameData
     */
    @IsLogin
    @Transactional
    public void updateHeadImg(String linkFilenameData) {
        User loginUser = LoginUserContext.getUser();
        loginUser.setAvatar(linkFilenameData);
        // 更新缓存中的登录用户信息
        updateCacheUser(loginUser);
        userRepository.update(loginUser);
    }

    /**
     * 登出
     * @param request
     */
    public void logout(UserTokenLogoutRequest request) {
        User user = delCacheLoginUser(request.getToken());
        if (ObjectUtils.isEmpty(user)) {
            return;
        }

        // 触发保存操作日志事件
        EventBus.emit(EventBus.Topic.USER_LOGOUT, OptLog.createUserLogoutRecordLog(user.getId(), JSON.toJSONString(request)));
    }

    private void updateCacheUser(User updateUser) {
        LoginUserContext.setUser(updateUser);
        cacheLoginUser(LoginUserContext.getToken(), updateUser);
    }


    public User delCacheLoginUser(String token) {
        String oldUser = cacheService.get(CacheBizTypeEn.USER_LOGIN_TOKEN, token);
        if (ObjectUtils.isEmpty(oldUser)) {
            return null;
        }

        User loginUser = JSON.parseObject(oldUser, User.class);
        cacheService.del(CacheBizTypeEn.USER_LOGIN_TOKEN, String.valueOf(loginUser.getId()));
        cacheService.del(CacheBizTypeEn.USER_LOGIN_TOKEN, token);

        return loginUser;
    }

}
