package top.erzhiqian.wechat.authentication.domain.facotory;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.erzhiqian.wechat.authentication.domain.entity.AppConfig;
import top.erzhiqian.wechat.authentication.domain.entity.MiniProgramAuthUser;
import top.erzhiqian.wechat.authentication.domain.repository.AppConfigRepository;
import top.erzhiqian.wechat.authentication.domain.repository.MiniProgramAuthRepository;
import top.erzhiqian.wechat.core.exception.BaseException;
import top.erzhiqian.wechat.core.exception.BaseExceptionCode;

import java.util.Optional;

@Component
public class AuthFactory {

    private MiniProgramAuthRepository miniProgramAuthRepository;


    private AppConfigRepository appConfigRepository;

    public AuthFactory(MiniProgramAuthRepository miniProgramAuthRepository, AppConfigRepository appConfigRepository) {
        this.miniProgramAuthRepository = miniProgramAuthRepository;
        this.appConfigRepository = appConfigRepository;
    }

    public MiniProgramAuthUser miniProgramAuthUser(String authCode) {
        MiniProgramAuthUser authUser = new MiniProgramAuthUser(authCode, miniProgramAuthRepository);
        return authUser;
    }

    public AppConfig loadAppConfigByAppId(String appId) {
        if (StringUtils.isEmpty(appId)) {
            throw new BaseException(BaseExceptionCode.INVALID_PARAM,"应用不存在。");
        }
        Optional<AppConfig> appConfig = appConfigRepository.findAppConfig(appId);
        appConfig.orElseThrow(() -> new BaseException(BaseExceptionCode.INVALID_PARAM,"应用不存在。"));
        return appConfig.get();
    }
}
