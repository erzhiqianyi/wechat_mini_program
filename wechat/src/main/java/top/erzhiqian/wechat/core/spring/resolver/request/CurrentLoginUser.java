package top.erzhiqian.wechat.core.spring.resolver.request;

import lombok.Data;
import top.erzhiqian.wechat.authentication.domain.entity.AuthUser;

@Data
public class CurrentLoginUser {
    private String token;

    private String openId;
    private String unionId;
    private String uuid;
    private String phone;
    private String wechatSessionKey;
    private String externalToken;
    private CurrentApp currentApp;

    public CurrentLoginUser(AuthUser authUser, String token) {
        this.token = token;
        this.openId = authUser.getOpenId();
        this.unionId = authUser.getUnionId();
        this.uuid = authUser.getUuid();
        this.phone =null != authUser.getPhone() ?  authUser.getPhone().number() : null;
    }
}
