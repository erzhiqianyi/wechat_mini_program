package top.erzhiqian.wechat.authentication.domain.entity;

import lombok.Getter;
import top.erzhiqian.wechat.core.domain.entity.BaseEntity;

@Getter
public class AppConfig extends BaseEntity {

    private String appId;

    private String appSecret;

    public AppConfig(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }


}
