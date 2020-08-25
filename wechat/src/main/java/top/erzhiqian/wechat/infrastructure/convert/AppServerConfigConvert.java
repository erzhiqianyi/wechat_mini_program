package top.erzhiqian.wechat.infrastructure.convert;

import org.springframework.stereotype.Component;
import top.erzhiqian.wechat.authentication.domain.entity.AppConfig;
import top.erzhiqian.wechat.scurity.domain.entity.ServerVerifyConfig;
import top.erzhiqian.wechat.scurity.domain.valueobject.MessageConfig;
import top.erzhiqian.wechat.scurity.domain.valueobject.SignType;
import top.erzhiqian.wechat.infrastructure.po.WechatAppConfigPO;

@Component
public class AppServerConfigConvert {

    public ServerVerifyConfig convertToServerVerifyEntity(WechatAppConfigPO configPO) {
        if (null == configPO) {
            return null;
        }
        MessageConfig.DataType dataType = MessageConfig.DataType.valueOf(configPO.getDataType());
        SignType signType = SignType.valueOf(configPO.getSignType());
        MessageConfig messageConfig = new MessageConfig(configPO.getUrl(), configPO.getToken(),
                configPO.getEncodingAESKey(),
                dataType, signType);
        ServerVerifyConfig config = new ServerVerifyConfig(configPO.getAppId(), messageConfig);
        config.identify(configPO.getId(), configPO.getCreateAt(), configPO.getLastModified());
        return config;
    }

    public AppConfig convertToAppConfigEntity(WechatAppConfigPO configPO) {
        if (null == configPO) {
            return null;
        }
        AppConfig appConfig = new AppConfig(configPO.getAppId(), configPO.getAppSecret());
        appConfig.identify(configPO.getId(), configPO.getCreateAt(), configPO.getLastModified());
        return appConfig;
    }

}
