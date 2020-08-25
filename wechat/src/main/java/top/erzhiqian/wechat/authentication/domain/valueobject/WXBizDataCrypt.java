package top.erzhiqian.wechat.authentication.domain.valueobject;

public class WXBizDataCrypt {
    private String appId;
    private String sessionKey;

    public WXBizDataCrypt(String appId, String sessionKey) {
        this.appId = appId;
        this.sessionKey = sessionKey;
    }

    /**
     * 校验数据真实性
     *
     * @param encryptedData 加密的用户数据
     * @param iv            与用户数据一同返回的初始向量
     * @return data 解密后的原文
     * 2020/8/25 16:25
     * 曹峰
     */
    public String decryptData(String encryptedData, String iv) {

        return null;
    }
}
