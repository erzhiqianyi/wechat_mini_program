package top.erzhiqian.wechat.authentication.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.erzhiqian.wechat.authentication.app.AuthenticationApp;
import top.erzhiqian.wechat.authentication.client.cmd.WechatMiniAuthCmd;
import top.erzhiqian.wechat.authentication.client.cmd.WechatNiniEncryptCmd;
import top.erzhiqian.wechat.authentication.client.dto.AuthenticationUserDTO;
import top.erzhiqian.wechat.core.spring.resolver.request.CurrentApp;
import top.erzhiqian.wechat.core.spring.resolver.request.CurrentLoginUser;
import top.erzhiqian.wechat.core.spring.resolver.request.CurrentMiniProgram;
import top.erzhiqian.wechat.core.spring.resolver.request.CurrentUser;

import java.util.Optional;

@RestController
@Log4j2
public class AuthenticationController {

    private AuthenticationApp authenticationApp;

    public AuthenticationController(AuthenticationApp authenticationApp) {
        this.authenticationApp = authenticationApp;
    }

    @PostMapping("authentication/wechat/miniprogram")
    public AuthenticationUserDTO authentication(@RequestBody WechatMiniAuthCmd cmd,
                                                @CurrentMiniProgram CurrentApp app) {

        if (null == app) {
            throw new IllegalArgumentException("服务繁忙，请稍后再试。");
        }
        cmd.setCurrentApp(app);
        Optional<AuthenticationUserDTO> authenticationUser = authenticationApp.authenticationByWechatCode(cmd);
        if (authenticationUser.isPresent()) {
            return authenticationUser.get();
        } else {
            return null;
        }
    }


    @PostMapping("authentication/wechat/miniprogram/phone")
    public AuthenticationUserDTO authentication(@RequestBody WechatNiniEncryptCmd cmd,
                                                @CurrentUser CurrentLoginUser user) {
        if (null == user) {
            throw new IllegalArgumentException("服务繁忙，请稍后再试。");
        }
        cmd.setCurrentLoginUser(user);
        authenticationApp.authenticationByWechatPhoneNumber(cmd);
        Optional<AuthenticationUserDTO> authenticationUser = authenticationApp.authenticationByWechatPhoneNumber(cmd);
        if (authenticationUser.isPresent()) {
            return authenticationUser.get();
        } else {
            return null;
        }
    }

}
