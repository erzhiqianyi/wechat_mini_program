package top.erzhiqian.wechat.infrastructure.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.erzhiqian.wechat.authentication.domain.entity.AuthUser;
import top.erzhiqian.wechat.authentication.domain.repository.AuthTokenRepository;
import top.erzhiqian.wechat.core.spring.resolver.request.CurrentLoginUser;
import top.erzhiqian.wechat.core.spring.resolver.request.LoadCurrentUserService;

import java.util.Optional;

@Service
public class LoadCurrentUserServiceImpl implements LoadCurrentUserService {


    private AuthTokenRepository authTokenRepository;

    public LoadCurrentUserServiceImpl(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    @Override
    public Optional<CurrentLoginUser> loadUser(String token) {
        if (StringUtils.isEmpty(token)) {
            return Optional.empty();
        }
        Optional<AuthUser> optional = authTokenRepository.loadUserByToken(token);
        if (!optional.isPresent()) {
            return Optional.empty();
        }
        AuthUser user = optional.get();
        boolean expired = user.expired();
        if (expired) {
            authTokenRepository.expiredAuthToken(token);
        }
        return Optional.of(new CurrentLoginUser(user, token));
    }
}
