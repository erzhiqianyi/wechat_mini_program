package top.erzhiqian.wechat.core.spring.resolver.request;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 增加方法的注入，将含有 @CurrentMiniProgram 注解的方法参数注入当前小程序AppId
 * 2020/8/25 10:06
 * 曹峰
 */
public class CurrentMiniProgramMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String HEADER_REFERER = "referer";

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(CurrentApp.class)
                && methodParameter.hasParameterAnnotation(CurrentMiniProgram.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String header = nativeWebRequest.getHeader(HEADER_REFERER);
        if (StringUtils.isEmpty(header)) {
            throw new MissingServletRequestPartException(HEADER_REFERER);
        }
        return CurrentApp.fromHeader(header);
    }
}
