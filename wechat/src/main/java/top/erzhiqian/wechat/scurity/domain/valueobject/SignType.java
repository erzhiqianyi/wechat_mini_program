package top.erzhiqian.wechat.scurity.domain.valueobject;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum SignType {
    SHA1 {
        @Override
        public String sign(String original) {
            if (StringUtils.isEmpty(original)) {
                return null;
            }
            return DigestUtils.sha1Hex(original);
        }
    },

    MD5_32 {
        @Override
        public String sign(String original) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(original.getBytes());
                return new BigInteger(1, md.digest()).toString(16);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public abstract String sign(String original);
}
