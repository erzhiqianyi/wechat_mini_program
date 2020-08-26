package top.erzhiqian.wechat.anticorruption.wechat.service;

import com.alibaba.fastjson.TypeReference;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.stereotype.Service;
import top.erzhiqian.wechat.anticorruption.wechat.valueobject.WechatBizDataCrypt;
import top.erzhiqian.wechat.anticorruption.wechat.valueobject.WechatPhoneNumber;
import top.erzhiqian.wechat.authentication.app.DecryptService;
import top.erzhiqian.wechat.authentication.client.cmd.WechatNiniEncryptCmd;
import top.erzhiqian.wechat.authentication.domain.valueobject.PhoneNumber;

import java.util.Optional;

@Service("wechatDecryptServiceImpl")
public class WechatDecryptServiceImpl implements DecryptService {

    @Override
    public <R> Optional<Object> decrypt(R encryptData, DecryptData decryptData) {
        if (!(encryptData instanceof WechatNiniEncryptCmd)) {
            return Optional.empty();
        }
        WechatNiniEncryptCmd cmd = (WechatNiniEncryptCmd) encryptData;
        WechatBizDataCrypt dataCrypt = new WechatBizDataCrypt(cmd.appId(), cmd.getCurrentLoginUser().getToken());
        switch (decryptData) {
            case PHONE_NUMBER:
                Optional<WechatPhoneNumber> optional = dataCrypt.decryptData(cmd.getEncryptedData(), cmd.getIv(),
                        new TypeReference<WechatPhoneNumber>() {
                        });
                return optional.map(item -> new PhoneNumber(optional.get().getPurePhoneNumber(),
                        optional.get().getCountryCode()));
        }
        return Optional.empty();
    }
}
