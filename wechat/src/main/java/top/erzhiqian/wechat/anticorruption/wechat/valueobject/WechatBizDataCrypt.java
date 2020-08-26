package top.erzhiqian.wechat.anticorruption.wechat.valueobject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

public class WechatBizDataCrypt {
    private static final int DEFAULT_KEY_LENGTH = 24;
    private static final String SECRET_KEY_SPEC = "AES";
    private static final String CIPHER_TYPE = "AES/CBC/PKCS5Padding";
    private String appId;
    private String sessionKey;

    public WechatBizDataCrypt(String appId, String sessionKey) {
        if (StringUtils.isEmpty(appId)) {
            throw new IllegalArgumentException("用用不存在。");
        }
        if (StringUtils.isEmpty(sessionKey)) {
            throw new IllegalArgumentException("sessionKey不存在");
        }
        if (sessionKey.length() != DEFAULT_KEY_LENGTH) {
            throw new IllegalArgumentException("sessionKey不正确");
        }
        this.appId = appId;
        this.sessionKey = sessionKey;
    }

    /**
     * 校验数据真实性
     * 1.对称解密使用的算法为 WXBizDataCrypt-128-CBC，数据采用PKCS#7填充。
     * 2.对称解密的目标密文为 Base64_Decode(encryptedData)。
     * 3.对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
     * 4.对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
     *
     * @param encryptedData 加密的用户数据
     * @param iv            与用户数据一同返回的初始向量
     * @param <T>           解密后的数据类型
     * @return data 解密后的原文
     * 2020/8/25 16:25
     * 曹峰
     */
    public <T extends WechatBizData> Optional<T> decryptData(String encryptedData, String iv,
                                                             TypeReference<T> type) {
        if (iv.length() != DEFAULT_KEY_LENGTH) {
            return Optional.empty();
        }
        Optional<String> decryptData = decrypt(encryptedData, iv);
        if (!decryptData.isPresent()) {
            return Optional.empty();
        }
        try {
            T result = JSON.parseObject(decryptData.get(), type);
            if (null == result) {
                return Optional.empty();
            }
            return validAppId(result.getWatermark().getAppId()) ?
                    Optional.of(result) : Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }

    private boolean validAppId(String otherAppId) {
        return Objects.equals(this.appId, otherAppId);
    }

    private Optional<String> decrypt(String encryptedData, String iv) {
        byte[] aesKey = Base64Utils.decode(sessionKey.getBytes());
        byte[] aesIv = Base64Utils.decode(iv.getBytes());
        byte[] aesCipher = Base64Utils.decode(encryptedData.getBytes());
        SecretKeySpec skeySpec = new SecretKeySpec(aesKey, SECRET_KEY_SPEC);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CIPHER_TYPE);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(aesIv);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
            byte[] original = cipher.doFinal(aesCipher);
            String result = new String(original);
            return Optional.of(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
