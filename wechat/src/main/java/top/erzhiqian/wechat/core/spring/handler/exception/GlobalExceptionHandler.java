package top.erzhiqian.wechat.core.spring.handler.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.erzhiqian.wechat.core.client.dto.ResultDTO;
import top.erzhiqian.wechat.core.exception.BaseException;
import top.erzhiqian.wechat.core.exception.BaseExceptionCode;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResultDTO exceptionHandler(Exception e) {
        log.error(e);
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return ResultDTO.fail(baseException.getCode());
        }
        return ResultDTO.fail(BaseExceptionCode.SYSTEM_ERROR);
    }
}
