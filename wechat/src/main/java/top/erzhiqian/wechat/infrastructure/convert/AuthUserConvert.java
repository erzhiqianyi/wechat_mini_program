package top.erzhiqian.wechat.infrastructure.convert;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.erzhiqian.wechat.authentication.domain.entity.AuthUser;
import top.erzhiqian.wechat.infrastructure.po.AuthTokenPO;

import java.time.Instant;

@Component
public class AuthUserConvert {

    public AuthTokenPO convertToPO(AuthUser authUser, String token) {
        if (null == authUser) {
            return null;
        }
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        AuthTokenPO authTokenPO = new AuthTokenPO();
        authTokenPO.setId(token);
        authTokenPO.setOpenId(authUser.getAppId());
        authTokenPO.setAppId(authUser.getAppId());
        authTokenPO.setCreateAt(Instant.now());
        authTokenPO.setLastModified(Instant.now());
        return authTokenPO;
    }

    public AuthUser convertToEntity(AuthTokenPO authTokenPO) {
        if (null == authTokenPO) {
            return null;
        }
        AuthUser authUser = new AuthUser(authTokenPO.getOpenId(), authTokenPO.getAppId());
        return authUser;
    }
}
