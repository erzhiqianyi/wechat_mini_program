package top.erzhiqian.wechat.scurity.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.wechat.scurity.client.cmd.WechatServerVerifyMessageCmd;
import top.erzhiqian.wechat.scurity.domain.entity.ServerVerifyConfig;
import top.erzhiqian.wechat.scurity.domain.repository.WechatServerConfigRepository;
import top.erzhiqian.wechat.scurity.domain.valueobject.WechatServerVerifyMessage;

import java.util.Optional;

@Component
public class ServerVerifyApp {


    private WechatServerConfigRepository repository;

    public ServerVerifyApp(WechatServerConfigRepository repository) {
        this.repository = repository;
    }

    public boolean verifyMessage(WechatServerVerifyMessageCmd cmd) {
        Optional<ServerVerifyConfig> configOptional = repository.findServerVerifyConfig(cmd.getAppId());
        if (!configOptional.isPresent()) {
            return false;
        }
        ServerVerifyConfig config = configOptional.get();
        WechatServerVerifyMessage message = new WechatServerVerifyMessage(
                cmd.getSignature(), cmd.getTimestamp(), cmd.getNonce(), cmd.getEchostr());
        boolean validMessage = config.checkWechatMessage(message);
        if (validMessage) {
            repository.updateVerifyStatus(config.id(),config.verified().name());
        }
        return validMessage;
    }
}
