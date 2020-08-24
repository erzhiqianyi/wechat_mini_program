package top.erzhiqian.wechat.scurity.domain.valueobject;

public class MessageConfig {

    private String url;

    private String token;

    private String encodingAESKey;

    private DataType dataType;

    private SignType signType;

    public MessageConfig(String url, String token, String encodingAESKey, DataType dataType, SignType signType) {
        this.url = url;
        this.token = token;
        this.encodingAESKey = encodingAESKey;
        this.dataType = dataType;
        this.signType = signType;
    }

    public enum DataType {
        XML,
        JSON
    }

    public String token() {
        return token;
    }


    public SignType signType() {
        return signType;
    }
}
