package top.erzhiqian.wechat.scurity.domain.valueobject;


import org.springframework.util.StringUtils;
import top.erzhiqian.wechat.core.exception.BaseException;
import top.erzhiqian.wechat.core.exception.BaseExceptionCode;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WechatServerVerifyMessage {
    private String signature;
    private String timestamp;
    private String nonce;
    private String echoStr;

    public WechatServerVerifyMessage(String signature, String timestamp, String nonce, String echoStr) {

        if (StringUtils.isEmpty(signature)) {
            throw new BaseException(BaseExceptionCode.INVALID_PARAM,"微信加密签名不能为空。");
        }

        if (StringUtils.isEmpty(timestamp)) {
            throw new BaseException(BaseExceptionCode.INVALID_PARAM,"时间戳不为空。");
        }

        if (StringUtils.isEmpty(nonce)) {
            throw new BaseException(BaseExceptionCode.INVALID_PARAM,"随机数不能为空。");
        }
        if (StringUtils.isEmpty(echoStr)) {
            throw new BaseException(BaseExceptionCode.INVALID_PARAM,"随机字符串不能为空。");
        }

        this.signature = signature;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.echoStr = echoStr;
    }


    public boolean checkSign(String token, SignType signType) {
        String sortParam = Stream
                .of(token, timestamp, nonce)
                .sorted()
                .collect(Collectors.joining());
        String sign = signType.sign(sortParam);
        return sign.equals(signature);
    }

}


