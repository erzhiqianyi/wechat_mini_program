package top.erzhiqian.wechat.anticorruption.wechat;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import top.erzhiqian.wechat.anticorruption.wechat.result.CodeSessionResult;
import top.erzhiqian.wechat.authentication.domain.repository.MiniProgramAuthRepository;
import top.erzhiqian.wechat.authentication.domain.valueobject.MiniProgramAuthSession;

import java.util.Optional;

@Service
@Log4j2
public class WechatRequestAdapt implements MiniProgramAuthRepository {

    private RestTemplate restTemplate;

    public WechatRequestAdapt(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<MiniProgramAuthSession> jsCode2Session(String jsCode, String appId, String appSecret) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + appId +
                "&secret=" + appSecret +
                "&js_code=" + jsCode +
                "&grant_type=authorization_code";
        String sessionResult = restTemplate.getForObject(url, String.class);
        log.info("微信认证结果: " + sessionResult);
        if (StringUtils.isEmpty(sessionResult)) {
            return Optional.empty();
        }
        CodeSessionResult result = JSON.parseObject(sessionResult, CodeSessionResult.class);
        if (!result.success()) {
            return Optional.empty();
        }
        return Optional
                .of(new MiniProgramAuthSession(result.getOpenid(), result.getUnionid(), result.getSession_key()));
    }
}
