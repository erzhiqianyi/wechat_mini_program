package top.erzhiqian.wechat.scurity.domain.entity;

import org.junit.Before;
import org.junit.Test;
import top.erzhiqian.wechat.scurity.domain.repository.WechatServerConfigRepository;
import top.erzhiqian.wechat.scurity.domain.valueobject.MessageConfig;
import top.erzhiqian.wechat.scurity.domain.valueobject.SignType;
import top.erzhiqian.wechat.scurity.domain.valueobject.WechatServerVerifyMessage;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServerVerifyConfigTest {
    private WechatServerConfigRepository repository;

    @Before
    public void init() {
        repository = new WechatServerConfigRepository() {
            @Override
            public Optional<ServerVerifyConfig> findServerVerifyConfig(String appId) {
                return Optional.empty();
            }

            @Override
            public void updateVerifyStatus(Long id, String status) {

            }
        };
    }

    @Test
    public void checkWechatMessage() {
        String appId = "wx9cc422768a1fb65f";
        String url = "https://erzhiqian.top/wechat/server/verify/" + appId;
        String token = "00b4de9a4da660c24b1";
        String encodingAESKey = "zb32BZklU50jP9Wm1xgaEYvB5WrySVyku8CodrwzDeI";
        MessageConfig.DataType dataType = MessageConfig.DataType.JSON;
        SignType signType = SignType.SHA1;
        MessageConfig messageConfig = new MessageConfig(url, token, encodingAESKey, dataType, signType);

        ServerVerifyConfig config = new ServerVerifyConfig(appId, messageConfig);
        String signature = "a2f6be9cea93c4d7e3ab38408b1a39574c765b9e";
        String timestamp = "1598248290";
        String nonce = "1688409320";
        String echostr = "7782515389461386722";
        WechatServerVerifyMessage message = new WechatServerVerifyMessage(signature, timestamp, nonce, echostr);
        boolean validMessage = config.checkWechatMessage(message);
        assertTrue(validMessage);

    }
}