package top.erzhiqian.wechat.authentication.domain.valueobject;

import com.alibaba.fastjson.TypeReference;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import top.erzhiqian.wechat.anticorruption.wechat.valueobject.WechatBizDataCrypt;
import top.erzhiqian.wechat.anticorruption.wechat.valueobject.WechatPhoneNumber;

import java.util.Optional;

import static org.junit.Assert.*;

@Log4j2
public class WechatBizDataCryptTest {

    @Test
    public void decryptData() {
        String sessionKey = "vy8fhO3pzBNIU2ZlHmsYZA==";
        String appId = "wx9cc422768a1fb65f";
        WechatBizDataCrypt wechatBizDataCrypt = new WechatBizDataCrypt(appId, sessionKey);
        String data = "R0cfkq3hQhoxMIMPDNPNTLdOB0gqaMV2xVFOYfMmaZSlsOoKd2geEKwK4ulX1yB9I7vqKKGrVMhXVru+HEnfvi04PUuPmtebOO/omvdebVNFOVN8/cLS2EgORgBTXdyY8t3e8rVLVn0+cvgeJLUyzH+1G6iNOaHx5yOXWYejEyrW3e7XhAUpzC/Mf0VXWZhqxY0x4hf+ZwNVok1MSy5H7Q==";
        String iv = "Uu496Vb5mQRERb5mApCASA==";
        Optional<WechatPhoneNumber> result = wechatBizDataCrypt.decryptData(data, iv,
                new TypeReference<WechatPhoneNumber>() {
                });
        assertTrue(result.isPresent());
        log.info(result.get());

    }
}