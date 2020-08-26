package top.erzhiqian.wechat.authentication.client.cmd;

import lombok.Data;
import top.erzhiqian.wechat.core.spring.resolver.request.CurrentApp;

import javax.validation.constraints.NotBlank;

@Data
public class WechatMiniAuthCmd {

    @NotBlank(message = "授权码不能为空")
    private String code;

    private CurrentApp currentApp;

}
