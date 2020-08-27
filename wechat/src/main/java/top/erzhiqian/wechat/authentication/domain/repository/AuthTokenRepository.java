package top.erzhiqian.wechat.authentication.domain.repository;

import top.erzhiqian.wechat.authentication.domain.entity.AuthUser;

import java.util.Optional;

public interface AuthTokenRepository {
    Optional<AuthUser> loadUserByToken(String token);

    boolean saveUserToken(AuthUser authUser, String token);

    void expiredAuthToken(String token);
}
