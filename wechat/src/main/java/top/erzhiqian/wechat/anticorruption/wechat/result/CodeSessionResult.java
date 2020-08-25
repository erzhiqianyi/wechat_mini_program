package top.erzhiqian.wechat.anticorruption.wechat.result;

import lombok.Data;

@Data
public class CodeSessionResult extends WechatResult{
    private String openid;
    private String session_key;
    private String unionid;

}
