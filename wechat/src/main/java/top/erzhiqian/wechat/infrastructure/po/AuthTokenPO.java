package top.erzhiqian.wechat.infrastructure.po;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import top.erzhiqian.wechat.core.infrastructure.po.UniqueIdPO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@RedisHash("wechat_auth_token")
@Table(name = AuthTokenPO.TABLE_NAME)
@Entity
public class AuthTokenPO extends UniqueIdPO {
    public static final String TABLE_NAME = "auth_token";

    private String openId;

    private String unionId;

    private String uuid;

    private String phone;

    private String appId;


}
