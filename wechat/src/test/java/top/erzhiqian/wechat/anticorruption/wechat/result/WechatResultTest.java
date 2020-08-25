package top.erzhiqian.wechat.anticorruption.wechat.result;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


public class WechatResultTest {

    @Test
    public void success() {
       WechatResult result = new WechatResult();
       Integer resultCode = 0;
       result.setErrcode(resultCode);
       assertTrue(result.success());
    }
}