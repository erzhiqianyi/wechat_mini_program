package top.erzhiqian.wechat.core.spring.resolver.request;

import lombok.Data;

@Data
public class CurrentLoginUser {
    private String token;
    private String openId;
    private String unionId;
    private String uuid;
    private CurrentApp currentApp;


    public CurrentLoginUser(String token) {
       this.token = token;
    }
}
