package top.erzhiqian.wechat.core.client.dto;

import lombok.Data;
import top.erzhiqian.wechat.core.exception.BaseExceptionCode;
import top.erzhiqian.wechat.core.exception.ExceptionCode;

@Data
public class ResultDTO<T> {
    private String msg;
    private int code;
    private T data;

    public ResultDTO() {
    }

    public ResultDTO(ExceptionCode exceptionCode) {
        this.code = exceptionCode.code();
        this.msg = exceptionCode.message();
    }

    public static <T> ResultDTO<T> success(T data) {
        ResultDTO<T> resultDTO = new ResultDTO<>(BaseExceptionCode.SUCCESS);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultDTO<T> fail(ExceptionCode exceptionCode) {
        return new ResultDTO<>(exceptionCode);
    }

}
