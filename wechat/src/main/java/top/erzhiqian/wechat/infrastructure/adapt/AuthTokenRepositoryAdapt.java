package top.erzhiqian.wechat.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.wechat.authentication.domain.entity.AuthUser;
import top.erzhiqian.wechat.authentication.domain.repository.AuthTokenRepository;
import top.erzhiqian.wechat.infrastructure.convert.AuthUserConvert;
import top.erzhiqian.wechat.infrastructure.po.AuthTokenPO;
import top.erzhiqian.wechat.infrastructure.repository.jdbc.AuthTokenJdbcRepository;
import top.erzhiqian.wechat.infrastructure.repository.redis.AuthTokenRedisRepository;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthTokenRepositoryAdapt implements AuthTokenRepository {

    private AuthTokenRedisRepository redisRepository;

    private AuthTokenJdbcRepository jdbcRepository;

    private AuthUserConvert convert;

    public AuthTokenRepositoryAdapt(AuthTokenRedisRepository redisRepository,
                                    AuthTokenJdbcRepository jdbcRepository,
                                    AuthUserConvert convert) {
        this.redisRepository = redisRepository;
        this.jdbcRepository = jdbcRepository;
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
        jdbcRepository.save(authTokenPO);
        return true;
    }

    @Override
    public void expiredAuthToken(String token) {
        redisRepository.deleteById(token);
        Optional<AuthTokenPO> authTokenPO = redisRepository.findById(token);
        authTokenPO.ifPresent(item -> {
            item.setLastModified(Instant.now());
            jdbcRepository.save(item);
        });
    }

}
