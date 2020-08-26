package top.erzhiqian.wechat.infrastructure.repository.jdbc;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.erzhiqian.wechat.infrastructure.po.WechatAppConfigPO;

import java.util.Optional;

@Repository
public interface AppServerConfigJdbcRepository extends CrudRepository<WechatAppConfigPO, Long> {
    Optional<WechatAppConfigPO> findByAppId(String appId);

    @Modifying
    @Query(" update WechatAppConfigPO  set status =:status where id =:id ")
    void updateVerifyStatus(@Param("id") Long id,@Param("status") String status);
}
