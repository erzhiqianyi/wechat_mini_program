package top.erzhiqian.wechat.authentication.domain.repository;

import top.erzhiqian.wechat.authentication.domain.entity.AppConfig;

import java.util.Optional;

public interface AppConfigRepository {
    Optional<AppConfig> findAppConfig(String appId);
}
