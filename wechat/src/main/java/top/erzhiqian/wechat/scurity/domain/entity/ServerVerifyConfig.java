package top.erzhiqian.wechat.scurity.domain.entity;

import top.erzhiqian.wechat.core.domain.entity.BaseEntity;
import top.erzhiqian.wechat.scurity.domain.repository.WechatServerConfigRepository;
import top.erzhiqian.wechat.scurity.domain.valueobject.MessageConfig;
import top.erzhiqian.wechat.scurity.domain.valueobject.WechatServerVerifyMessage;

public class ServerVerifyConfig extends BaseEntity {

    private String appId;

    private MessageConfig config;

    private Status status;

    public ServerVerifyConfig(String appId, MessageConfig config) {
        this.appId = appId;
        this.config = config;
    }

    public boolean checkWechatMessage(WechatServerVerifyMessage message) {
        boolean validSign = message.checkSign(config.token(), config.signType());
        return validSign;
    }

    public Status verified() {
        this.status = Status.VERIFIED;
        return this.status;
    }

    public enum Status {
        CREATED,
        VERIFIED;
    }
}
