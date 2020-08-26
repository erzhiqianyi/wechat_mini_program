package top.erzhiqian.wechat.anticorruption.wechat.valueobject;

import lombok.Data;

@Data
public class WechatPhoneNumber extends WechatBizData {
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;

}
