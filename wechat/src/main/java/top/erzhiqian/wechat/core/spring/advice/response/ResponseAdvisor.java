package top.erzhiqian.wechat.core.spring.advice.response;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.erzhiqian.wechat.core.client.dto.ResultDTO;
import top.erzhiqian.wechat.core.exception.BaseExceptionCode;

@ControllerAdvice
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        boolean classIgnoreResponseAdvice = methodParameter.getDeclaringClass()
                .isAnnotationPresent(IgnoreResponseAdvice.class);
        if (classIgnoreResponseAdvice) {
            return false;
        }

        boolean methodIgnoreResponseAdvice = methodParameter.getMethod()
                .isAnnotationPresent(IgnoreResponseAdvice.class);
        if (methodIgnoreResponseAdvice) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (null == data) {
            return ResultDTO.fail(BaseExceptionCode.DATA_NOT_EXISTS);
        }

        if (data instanceof ResultDTO) {
            return data;
        }
        if (data instanceof String) {
            return JSON.toJSON(ResultDTO.success(data)).toString();
        }

        return ResultDTO.success(data);
    }
}
