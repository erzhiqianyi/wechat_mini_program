package top.erzhiqian.wechat.authentication.domain.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.wechat.authentication.domain.valueobject.MiniProgramAuthSession;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class MiniProgramAuthRepositoryTest {

    @Autowired
    private MiniProgramAuthRepository repository;

    @Test
    public void jsCode2Session() {
        String authCode = "093NWCkl2KD5v54bHKll26NQ9q0NWCk4";
        String appId = "wx9cc422768a1fb65f";
        String appSecret = "fa1d5233a545bf9bde9dea40782b988a";
        Optional<MiniProgramAuthSession> authSession = repository.jsCode2Session(authCode, appId, appSecret);
        assertTrue(authSession.isPresent());
    }
}