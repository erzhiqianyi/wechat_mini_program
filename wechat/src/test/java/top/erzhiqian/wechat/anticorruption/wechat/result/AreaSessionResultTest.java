package top.erzhiqian.wechat.anticorruption.wechat.result;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

@Log4j2
public class AreaSessionResultTest {

    @Test
    public void jsonToSessionResult() {
        String json = "{\"session_key\":\"zQVp5F0Og+f1dFixrHBWKA==\",\"openid\":\"ornoe5AR1_z84dJbw7w-nmvh2chk\"}";
        CodeSessionResult result = JSON.parseObject(json, CodeSessionResult.class);
        log.info(result);

    }

}