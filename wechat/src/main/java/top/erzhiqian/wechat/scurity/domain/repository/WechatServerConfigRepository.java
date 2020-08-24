package top.erzhiqian.wechat.scurity.domain.repository;

import top.erzhiqian.wechat.scurity.domain.entity.ServerVerifyConfig;

import java.util.Optional;

public interface WechatServerConfigRepository {
    Optional<ServerVerifyConfig> findServerVerifyConfig(String appId);

    void updateVerifyStatus(Long id, String status);
}
