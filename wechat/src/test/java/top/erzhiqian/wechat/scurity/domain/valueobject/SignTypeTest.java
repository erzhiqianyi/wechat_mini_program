package top.erzhiqian.wechat.scurity.domain.valueobject;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
class SignTypeTest {

    @Test
    void signSha1() {
        String original = "测试";
        String expected = "0b5d7ed54bee16756a7579c6718ab01e3d1b75eb";
        String sign = SignType.SHA1.sign(original);
        assertEquals(expected, sign);
    }

    @Test
    public void signMd532() {
        String original = "测试";
        String sign = SignType.MD5_32.sign(original);
        String expected = "db06c78d1e24cf708a14ce81c9b617ec";
        assertEquals(expected, sign);
    }

    @Test
    public void sha1(){
       String str = "wx9cc422768a1fb65f" ;
       log.info(SignType.SHA1.sign(str));
    }
}