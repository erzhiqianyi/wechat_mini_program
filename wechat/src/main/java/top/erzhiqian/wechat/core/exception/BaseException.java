package top.erzhiqian.wechat.core.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    protected ExceptionCode code;

    public BaseException(String message) {
        super(message);
    }
    public BaseException(ExceptionCode code,String message) {
        super(message);
        this.code = code;

    }


    public BaseException(ExceptionCode code) {
        super(code.message());
        this.code = code;
    }

}
