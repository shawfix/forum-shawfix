package pub.shawfix.forum.facade.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pub.shawfix.forum.app.support.LoginUserContext;
import pub.shawfix.forum.common.constant.Constant;
import pub.shawfix.forum.common.enums.CacheBizTypeEn;
import pub.shawfix.forum.domain.entity.User;
import pub.shawfix.forum.domain.service.CacheService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author shawfix
 * @create 2025/5/30 08:40
 * @desc
 **/
@Slf4j
@Component
@Aspect
public class LoginUserAspect {

    @Resource
    private CacheService cacheService;

    @Resource
    private HttpServletRequest httpServletRequest;

    @Around("execution(* pub.shawfix.forum.api..*.*(..))")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        String token = httpServletRequest.getHeader(Constant.REQUEST_HEADER_TOKEN_KEY);

        if (ObjectUtils.isEmpty(token)) {
            Object tokenObj = httpServletRequest.getAttribute(Constant.REQUEST_QUERY_TOKEN_KEY);
            if (ObjectUtils.isEmpty(token)) {
                return joinPoint.proceed();
            }

            token = String.valueOf(tokenObj);
        }

        String cacheString = cacheService.get(CacheBizTypeEn.USER_LOGIN_TOKEN, token);
        if (ObjectUtils.isEmpty(cacheString)) {
            return joinPoint.proceed();
        }

        LoginUserContext.setToken(token);
        LoginUserContext.setUser(JSON.parseObject(cacheString, User.class));

        try {
            return joinPoint.proceed();
        } finally {
            LoginUserContext.removeAll();
        }
    }
}
