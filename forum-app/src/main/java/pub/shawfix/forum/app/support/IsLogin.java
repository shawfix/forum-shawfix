package pub.shawfix.forum.app.support;

import pub.shawfix.forum.common.enums.UserRoleEn;

import java.lang.annotation.*;

/**
 * @author shawfix
 * @create 2025/5/29 15:01
 * @desc
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IsLogin {

    /**
     * 登录角色
     * @return
     */
    UserRoleEn role() default UserRoleEn.USER;

}
