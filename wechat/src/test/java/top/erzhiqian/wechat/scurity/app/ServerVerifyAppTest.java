package top.erzhiqian.wechat.scurity.app;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.wechat.scurity.client.cmd.WechatServerVerifyMessageCmd;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class ServerVerifyAppTest {

    @Autowired
    private ServerVerifyApp serverVerifyApp;


    @Test
    public void verifyMessage() {
        String signature = "a2f6be9cea93c4d7e3ab38408b1a39574c765b9e";
        String timestamp = "1598248290";
        String nonce = "1688409320";
        String echostr = "7782515389461386722";
        String appId = "wx9cc422768a1fb65f";
        WechatServerVerifyMessageCmd cmd = new WechatServerVerifyMessageCmd(signature,timestamp,nonce,echostr);
        cmd.setAppId(appId);
        boolean validMessage = serverVerifyApp.verifyMessage(cmd);
        assertTrue(validMessage);
    }
}