package top.erzhiqian.wechat.infrastructure.repository.redis;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.wechat.infrastructure.po.AuthTokenPO;

import java.time.Instant;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class AuthTokenRedisRepositoryTest {

    @Autowired
    private AuthTokenRedisRepository authTokenRedisRepository;

    @Test
    public void findByToken() {
        AuthTokenPO po = new AuthTokenPO();
        po.setId("fsd");
        po.setAppId("fsdfsdklk");
        po.setCreateAt(Instant.now());
        po.setOpenId("fdjgdslg");
        po.setUnionId("e43453");
        po.setUuid("ufdsfds");
        po.setPhone("131435124204");
        authTokenRedisRepository.save(po);
        Optional<AuthTokenPO> authTokenPO = authTokenRedisRepository.findById(po.getId());
        assertTrue(authTokenPO.isPresent());
    }
}