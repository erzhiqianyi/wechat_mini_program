package top.erzhiqian.wechat.authentication.domain.entity;

import lombok.Getter;
import top.erzhiqian.wechat.authentication.domain.valueobject.PhoneNumber;
import top.erzhiqian.wechat.core.domain.entity.BaseEntity;

@Getter
public class AuthUser extends BaseEntity {

    private String openId;

    private String unionId;

    private String uuid;

    private PhoneNumber phone;

    private String appId;

    public AuthUser(String openId, String appId) {
        this.openId = openId;
        this.appId = appId;
    }
}
