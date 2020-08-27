package top.erzhiqian.wechat.authentication.domain.entity;

import lombok.Getter;
import top.erzhiqian.wechat.authentication.domain.valueobject.PhoneNumber;
import top.erzhiqian.wechat.core.domain.entity.BaseEntity;

import java.time.Instant;

@Getter
public class AuthUser extends BaseEntity {

    private String openId;

    private String unionId;

    private String uuid;

    private PhoneNumber phone;

    private String appId;

    private Instant createTime;

    private Integer authAmount;

    public AuthUser(String openId, String appId) {
        this.openId = openId;
        this.appId = appId;
    }

    public boolean expired() {

        return false;
    }
}
