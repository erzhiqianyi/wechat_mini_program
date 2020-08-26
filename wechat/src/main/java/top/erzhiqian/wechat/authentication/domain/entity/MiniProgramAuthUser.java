package top.erzhiqian.wechat.authentication.domain.entity;

import org.springframework.util.StringUtils;
import top.erzhiqian.wechat.authentication.domain.repository.MiniProgramAuthRepository;
import top.erzhiqian.wechat.authentication.domain.valueobject.MiniProgramAuthSession;
import top.erzhiqian.wechat.core.domain.entity.BaseEntity;
import top.erzhiqian.wechat.core.exception.BaseExceptionCode;

import java.util.Optional;

public class MiniProgramAuthUser extends BaseEntity {

    private MiniProgramAuthRepository repository;

    private MiniProgramAuthSession session;

    private String authCode;

    public MiniProgramAuthUser(String authCode, MiniProgramAuthRepository repository) {
        if (StringUtils.isEmpty(authCode)) {
            throw new IllegalArgumentException(BaseExceptionCode.NULL_PARAM.message());
        }
        this.authCode = authCode;
        this.repository = repository;
    }

    public boolean auth(String appId, String appSecret) {
        Optional<MiniProgramAuthSession> authSession = repository.jsCode2Session(authCode, appId, appSecret);
        authSession.ifPresent(session -> this.session = session);
        return authSession.isPresent();
    }

    public String  currentSessionKey(){
        return null != session ?  session.getSessionKey() : null;
    }

    public String openId(){
        return  null != session ? session.getOpenId() : null;
    }

}

