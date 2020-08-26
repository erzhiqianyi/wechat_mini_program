package top.erzhiqian.wechat.core.spring.resolver.request;

import java.util.Optional;

/**
 * 根据token加载当前用户
 * 2020/8/26 15:52
 * 曹峰
 */
public interface LoadCurrentUserService {
    Optional<CurrentLoginUser> loadUser(String token);
}
