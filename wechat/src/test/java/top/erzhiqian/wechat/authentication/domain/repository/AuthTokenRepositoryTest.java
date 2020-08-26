package top.erzhiqian.wechat.authentication.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.wechat.authentication.domain.entity.AuthUser;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTokenRepositoryTest {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Test
    public void loadUserByToken() {
        String openId = "fdsaf";
        String appId = "3243245";
        AuthUser authUser = new AuthUser(openId,appId);
        String token  = "fdsfsddsg" ;
        authTokenRepository.saveUserToken(authUser,token);
    }

    @Test
    public void saveUserToken() {

    }
}