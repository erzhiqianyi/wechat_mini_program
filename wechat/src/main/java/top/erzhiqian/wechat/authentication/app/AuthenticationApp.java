package top.erzhiqian.wechat.authentication.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.wechat.authentication.client.cmd.WechatMiniAuthCmd;
import top.erzhiqian.wechat.authentication.client.dto.AuthenticationUserDTO;
import top.erzhiqian.wechat.authentication.domain.entity.AppConfig;
import top.erzhiqian.wechat.authentication.domain.entity.MiniProgramAuthUser;
import top.erzhiqian.wechat.authentication.domain.facotory.AuthFactory;

import java.util.Optional;

@Component
public class AuthenticationApp {

    private AuthFactory factory;

    public AuthenticationApp(AuthFactory factory) {
        this.factory = factory;
    }

    public Optional<AuthenticationUserDTO> authentication(WechatMiniAuthCmd cmd) {
        AppConfig appConfig = factory.loadAppConfigByAppId(cmd.getApp().getAppId());
        MiniProgramAuthUser authUser = factory.miniProgramAuthUser(cmd.getCode());
        boolean validCode = authUser.auth(appConfig.getAppId(), appConfig.getAppSecret());
        if (!validCode) {
            return Optional.empty();
        }
        //todo 把token 存入redis中
        AuthenticationUserDTO authenticationUser = new AuthenticationUserDTO();
        authenticationUser.setToken(authUser.currentSessionKey());
        return Optional.of(authenticationUser);
    }
}
