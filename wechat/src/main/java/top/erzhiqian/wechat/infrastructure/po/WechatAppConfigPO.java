package top.erzhiqian.wechat.infrastructure.po;

import lombok.Data;
import top.erzhiqian.wechat.core.infrastructure.po.BasePO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = WechatAppConfigPO.TABLE_NAME)
@Entity
public class WechatAppConfigPO extends BasePO {
    public static final String TABLE_NAME ="wechat_app_config";

    @Column(columnDefinition = "varchar(50) not null comment '公众号或小程序AppID' ", unique = true)
    private String appId;

    @Column(columnDefinition = "varchar(50) not null comment 'AppSecret(小程序密钥)' ", unique = true)
    private String appSecret;

    @Column(columnDefinition = "varchar(500) not null comment 'URL(服务器地址)' ")
    private String url;

    @Column(columnDefinition = "varchar(32) not null comment 'Token(令牌)' ")
    private String token;

    @Column(columnDefinition = "varchar(50) not null comment 'EncodingAESKey(消息加密密钥)' ")
    private String encodingAESKey ;


    @Column(columnDefinition = "varchar(5000) default null comment '代码上传密钥' ")
    private String uploadKey;

    @Column(columnDefinition = "varchar(50) not null comment '消息加密方式' ")
    private String signType;

    @Column(columnDefinition = "varchar(50) default 'CREATED' comment '状态' ")
    private String status;

    @Column(columnDefinition = "varchar(50) not null comment '数据格式' ")
    private String dataType;



}
