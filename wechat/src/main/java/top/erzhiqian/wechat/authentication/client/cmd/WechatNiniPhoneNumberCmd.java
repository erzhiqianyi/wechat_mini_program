package top.erzhiqian.wechat.authentication.client.cmd;

import lombok.Data;
import top.erzhiqian.wechat.core.spring.resolver.request.CurrentApp;

import javax.validation.constraints.NotBlank;

@Data
public class WechatNiniPhoneNumberCmd {

    @NotBlank(message = "加密数据不能为空")
    private String  encryptedData;

    private String errMsg;

    @NotBlank(message = "加密算法的初始向量不能为空")
    private String iv;

    private CurrentApp currentApp;
}
