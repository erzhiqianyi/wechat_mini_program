package top.erzhiqian.wechat.infrastructure.repository.jdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.erzhiqian.wechat.infrastructure.po.AuthTokenPO;

@Repository
public interface AuthTokenJdbcRepository  extends CrudRepository<AuthTokenPO, String> {
}
