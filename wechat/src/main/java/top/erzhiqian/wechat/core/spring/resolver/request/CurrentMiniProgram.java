package top.erzhiqian.wechat.core.spring.resolver.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在controller 使用该注解，获取当前使用的小程序 appId
 * 2020/8/25 10:02
 * 曹峰
*/
@Target(ElementType.PARAMETER)          // 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME)     // 运行时有效
public @interface CurrentMiniProgram {

}
