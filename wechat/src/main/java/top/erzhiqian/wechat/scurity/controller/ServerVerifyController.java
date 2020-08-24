package top.erzhiqian.wechat.scurity.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.erzhiqian.wechat.scurity.app.ServerVerifyApp;
import top.erzhiqian.wechat.scurity.client.cmd.WechatServerVerifyMessageCmd;

/**
 * 微信消息推送配置,第三方可以不用开启
 * 具体参考微信文档
 * <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/server-ability/message-push.html">消息推送</a>
 * 2020/8/24 10:30
 * 曹峰
 */
@RestController
@Log4j2
public class ServerVerifyController {

    private ServerVerifyApp serverVerifyApp;

    public ServerVerifyController(ServerVerifyApp serverVerifyApp) {
        this.serverVerifyApp = serverVerifyApp;
    }

    @GetMapping("server/verify/{appId}")
    public String verifyServer(String signature, String timestamp, String nonce, String echostr,
                               @PathVariable("appId") String appId) {
        WechatServerVerifyMessageCmd cmd = new WechatServerVerifyMessageCmd(signature, timestamp, nonce, echostr);
        cmd.setAppId(appId);
        boolean validMessage = serverVerifyApp.verifyMessage(cmd);
        if (validMessage) {
            return cmd.getEchostr();
        } else {
            return null;
        }
    }
}
