package top.erzhiqian.wechat.authentication.app;

import top.erzhiqian.wechat.core.spring.config.ApplicationContextHolder;

import java.util.Optional;

public interface DecryptService {
    <R> Optional<Object> decrypt(R encryptData, DecryptData decryptData);

    enum DecryptApp {
        WECHAT {
            @Override
            public String service() {
                return "wechatDecryptServiceImpl";
            }
        };

        public abstract String service();
    }

    enum DecryptData {
        PHONE_NUMBER;
    }

    static DecryptService createDecryptService(DecryptService.DecryptApp app) {
        if (null == app) {
            return null;
        }
        DecryptService decryptService = ApplicationContextHolder.getBean(app.service(), DecryptService.class);
        if (null == decryptService) {
            throw new IllegalArgumentException("没有相应解密服务，请先配置解密服务。");
        }
        return decryptService;
    }
}
