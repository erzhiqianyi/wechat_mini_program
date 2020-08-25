package top.erzhiqian.wechat.authentication.domain.valueobject;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MiniProgramAuthSession {

    private String openId;

    private String unionId;

    private String sessionKey;


    public MiniProgramAuthSession(String openId, String unionId, String sessionKey) {
        this.openId = openId;
        this.unionId = unionId;
        this.sessionKey = sessionKey;
    }
}
