package top.erzhiqian.wechat.infrastructure.po;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.Instant;

@Data
@RedisHash("auth_token")
public class AuthTokenPO {

    @Id
    private String id;

    private String openId;

    private String unionId;

    private String uuid;

    private String phone;

    private String appId;

    private Instant createTime;

}
