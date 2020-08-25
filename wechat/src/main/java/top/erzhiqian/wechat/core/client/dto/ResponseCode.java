package top.erzhiqian.wechat.core.client.dto;

public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    BAD_REQUEST(4,"BAE_REQUEST"),
    ERROR(5,"ERROR");
    private int ret;
    private String msg;

    ResponseCode(int ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }
}
