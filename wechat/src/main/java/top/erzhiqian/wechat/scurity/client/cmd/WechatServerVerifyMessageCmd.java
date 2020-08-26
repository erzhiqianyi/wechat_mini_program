package top.erzhiqian.wechat.scurity.client.cmd;

import lombok.Data;
import org.springframework.util.StringUtils;
import top.erzhiqian.wechat.core.exception.BaseException;
import top.erzhiqian.wechat.core.exception.BaseExceptionCode;

@Data
public class WechatServerVerifyMessageCmd {

    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;
    private String appId;

    public WechatServerVerifyMessageCmd() {

    }

    public WechatServerVerifyMessageCmd(String signature, String timestamp, String nonce, String echosStr) {
        if (StringUtils.isEmpty(signature)) {
            throw new BaseException(BaseExceptionCode.INVALID_PARAM,"微信加密签名不能为空。");
        }

        if (StringUtils.isEmpty(timestamp)) {
            throw new BaseException(BaseExceptionCode.INVALID_PARAM,"时间戳不为空。");
        }

        if (StringUtils.isEmpty(nonce)) {
            throw new BaseException(BaseExceptionCode.INVALID_PARAM,"随机数不能为空。");
        }
        if (StringUtils.isEmpty(echosStr)) {
            throw new BaseException(BaseExceptionCode.INVALID_PARAM,"随机字符串不能为空。");
        }
        this.signature = signature;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.echostr = echosStr;
    }


}
