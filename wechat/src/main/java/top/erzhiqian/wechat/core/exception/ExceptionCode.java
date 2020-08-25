package top.erzhiqian.wechat.core.exception;

public interface ExceptionCode {
    /**
     * 异常代码
     *
     * @return code 错误代码
     */
    int code();

    /**
     * 异常信息
     *
     * @return msg 异常信息
     */
    String message();
}
