package top.erzhiqian.wechat.core.infrastructure.po;

import lombok.Data;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Data
@MappedSuperclass
public class ExpirePO extends UniqueIdPO {


    @TimeToLive
    protected Instant expiredAt;

}
