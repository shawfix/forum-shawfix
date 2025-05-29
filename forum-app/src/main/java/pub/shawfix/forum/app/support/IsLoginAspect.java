package pub.shawfix.forum.app.support;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pub.shawfix.forum.common.enums.ErrorCodeEn;
import pub.shawfix.forum.common.enums.UserRoleEn;
import pub.shawfix.forum.common.exception.BizException;
import pub.shawfix.forum.common.support.CheckUtil;
import pub.shawfix.forum.domain.entity.User;

/**
 * @author shawfix
 * @create 2025/5/29 15:05
 * @desc
 **/
@Component
@Aspect
public class IsLoginAspect {

    /**
     * 获取缓存的登录用户信息
     * 判断当前登录用户的角色权限，并将登录用户设置到请求线程上下文中供业务处理时直接获取
     * @param joinPoint
     * @param isLogin
     * @return
     * @throws Throwable
     */
    @Around("execution(* pub.shawfix.forum..*.*(..)) && @annotation(isLogin)")
    public Object process(ProceedingJoinPoint joinPoint, IsLogin isLogin) throws Throwable {
        User loginUser = LoginUserContext.getUser();
        CheckUtil.isEmpty(loginUser, ErrorCodeEn.USER_NOT_LOGIN);

        UserRoleEn userRoleEn = loginUser.getRole();

        // 超级管理员
        if (UserRoleEn.SUPER_ADMIN.equals(isLogin.role())) {
            if (UserRoleEn.SUPER_ADMIN.equals(userRoleEn)) {
                return joinPoint.proceed();
            }

            throw new BizException(ErrorCodeEn.COMMON_TOKEN_NO_PERMISSION);
        }

        // 管理员
        else if (UserRoleEn.ADMIN.equals(isLogin.role())) {
            if (UserRoleEn.SUPER_ADMIN.equals(userRoleEn) || UserRoleEn.ADMIN.equals(userRoleEn)) {
                return joinPoint.proceed();
            }

            throw new BizException(ErrorCodeEn.COMMON_TOKEN_NO_PERMISSION);
        }

        // 用户
        return joinPoint.proceed();
    }
}
