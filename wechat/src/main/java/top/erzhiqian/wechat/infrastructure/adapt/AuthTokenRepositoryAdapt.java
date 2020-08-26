package top.erzhiqian.wechat.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.wechat.authentication.domain.entity.AuthUser;
import top.erzhiqian.wechat.authentication.domain.repository.AuthTokenRepository;
import top.erzhiqian.wechat.infrastructure.convert.AuthUserConvert;
import top.erzhiqian.wechat.infrastructure.po.AuthTokenPO;
import top.erzhiqian.wechat.infrastructure.repository.redis.AuthTokenRedisRepository;

import java.util.Optional;

@Service
public class AuthTokenRepositoryAdapt implements AuthTokenRepository {

    private AuthTokenRedisRepository redisRepository;

    private AuthUserConvert convert;

    public AuthTokenRepositoryAdapt(AuthTokenRedisRepository redisRepository, AuthUserConvert convert) {
        this.redisRepository = redisRepository;
        this.convert = convert;
    }

    @Override
    public Optional<AuthUser> loadUserByToken(String token) {
        Optional<AuthTokenPO> authTokenPO = redisRepository.findById(token);
        return authTokenPO.map(convert::convertToEntity);
    }

    @Override
    public boolean saveUserToken(AuthUser authUser, String token) {
        AuthTokenPO authTokenPO = convert.convertToPO(authUser, token);
        redisRepository.save(authTokenPO);
        return true;
    }

}
