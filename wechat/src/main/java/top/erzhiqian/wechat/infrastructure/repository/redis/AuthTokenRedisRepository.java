package top.erzhiqian.wechat.infrastructure.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.erzhiqian.wechat.infrastructure.po.AuthTokenPO;

@Repository
public interface AuthTokenRedisRepository extends CrudRepository<AuthTokenPO, String> {
}
