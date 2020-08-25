
package top.erzhiqian.wechat.authentication.domain.entity;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import top.erzhiqian.wechat.authentication.domain.repository.MiniProgramAuthRepository;
import top.erzhiqian.wechat.authentication.domain.valueobject.MiniProgramAuthSession;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@Log4j2
public class MiniProgramAuthUserTest {

    private MiniProgramAuthRepository repository;

    private RestTemplate restTemplate;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
        repository = (jsCode, appId, appSecret) -> {
            String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=" + appId +
                    "&secret=" + appSecret +
                    "&js_code=" + jsCode +
                    "&grant_type=authorization_code";
            String result = restTemplate.getForObject(url, String.class);
            log.info(result);
            MiniProgramAuthSession session = JSON.parseObject(result,MiniProgramAuthSession.class);
            log.info(session);
            return Optional.empty();
        };
    }

    @Test
    public void auth() {
        String authCode = "093NWCkl2KD5v54bHKll26NQ9q0NWCk4";
        String appId = "wx9cc422768a1fb65f";
        String appSecret = "fa1d5233a545bf9bde9dea40782b988a";
        MiniProgramAuthUser authUser = new MiniProgramAuthUser(authCode, repository);
        boolean validCode = authUser.auth(appId, appSecret);
        assertTrue(validCode);
    }

}