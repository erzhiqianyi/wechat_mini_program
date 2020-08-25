package top.erzhiqian.wechat.core.exception;

public enum BaseExceptionCode implements ExceptionCode {
    SUCCESS(0, "success"),
    SYSTEM_ERROR(10000, "service unavailable"),
    DATA_NOT_EXISTS(10001, "no data"),
    NULL_PARAM(10002,"invalid param"),
    CONFIG_NOT_EXISTS(10003,"config not exists.");
    private int code;
    private String message;

    BaseExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
