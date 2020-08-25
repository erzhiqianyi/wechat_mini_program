package top.erzhiqian.wechat.anticorruption.wechat.result;

import lombok.Data;

@Data
public class WechatResult {
    private static final int SUCCESS_CODE = 0;
    private Integer errcode;

    private String errmsg;

    public boolean success() {

        return null == errcode || errcode == SUCCESS_CODE;
    }
}
