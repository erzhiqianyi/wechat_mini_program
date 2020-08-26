package top.erzhiqian.wechat.authentication.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.wechat.authentication.client.cmd.WechatMiniAuthCmd;
import top.erzhiqian.wechat.authentication.client.cmd.WechatNiniEncryptCmd;
import top.erzhiqian.wechat.authentication.client.dto.AuthenticationUserDTO;
import top.erzhiqian.wechat.authentication.domain.entity.AppConfig;
import top.erzhiqian.wechat.authentication.domain.entity.AuthUser;
import top.erzhiqian.wechat.authentication.domain.entity.MiniProgramAuthUser;
import top.erzhiqian.wechat.authentication.domain.facotory.AuthFactory;
import top.erzhiqian.wechat.authentication.domain.repository.AuthTokenRepository;
import top.erzhiqian.wechat.authentication.domain.valueobject.PhoneNumber;
import top.erzhiqian.wechat.core.exception.BaseException;
import top.erzhiqian.wechat.core.exception.BaseExceptionCode;

import java.util.Optional;

@Component
public class AuthenticationApp {

    private AuthFactory factory;


    private AuthTokenRepository authTokenRepository;

    public AuthenticationApp(AuthFactory factory, AuthTokenRepository authTokenRepository) {
        this.factory = factory;
        this.authTokenRepository = authTokenRepository;
    }

    public Optional<AuthenticationUserDTO> authenticationByWechatCode(WechatMiniAuthCmd cmd) {
        AppConfig appConfig = factory.loadAppConfigByAppId(cmd.getCurrentApp().getAppId());
        MiniProgramAuthUser wechatUser = factory.miniProgramAuthUser(cmd.getCode());
        boolean validCode = wechatUser.auth(appConfig.getAppId(), appConfig.getAppSecret());
        if (!validCode) {
            return Optional.empty();
        }
        AuthUser authUser = new AuthUser(wechatUser.openId(), cmd.getCurrentApp().getAppId());
        //将用户信息存入 redis
        boolean saveAuthUser = authTokenRepository.saveUserToken(authUser, wechatUser.currentSessionKey());
        if (!saveAuthUser) {
            throw new BaseException(BaseExceptionCode.SYSTEM_ERROR,"服务不可用，请稍后再试。");
        }
        // todo 判断是否需要获取手机号 根据unionId或者openId去判断
        AuthenticationUserDTO authenticationUser = new AuthenticationUserDTO();
        authenticationUser.setToken(wechatUser.currentSessionKey());
        return Optional.of(authenticationUser);
    }

    public Optional<AuthenticationUserDTO> authenticationByWechatPhoneNumber(WechatNiniEncryptCmd cmd) {
        DecryptService decryptService = DecryptService.createDecryptService(DecryptService.DecryptApp.WECHAT);
        Optional optional = decryptService.decrypt(cmd, DecryptService.DecryptData.PHONE_NUMBER);
        if (!optional.isPresent()) {
            return Optional.empty();
        }
        PhoneNumber phoneNumber = (PhoneNumber) optional.get();
        //todo 更新用户手机号
        AuthenticationUserDTO authenticationUser = new AuthenticationUserDTO();
        authenticationUser.setToken(phoneNumber.number());
        authenticationUser.setNeedPhone(false);
        return Optional.of(authenticationUser);
    }
}
