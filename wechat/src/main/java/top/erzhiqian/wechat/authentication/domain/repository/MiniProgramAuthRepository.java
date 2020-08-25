package top.erzhiqian.wechat.authentication.domain.repository;

import top.erzhiqian.wechat.authentication.domain.valueobject.MiniProgramAuthSession;

import java.util.Optional;

public interface MiniProgramAuthRepository {
    Optional<MiniProgramAuthSession> jsCode2Session(String jsCode, String appId, String appSecret);
}

