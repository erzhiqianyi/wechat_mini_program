package top.erzhiqian.wechat.core.spring.resolver.request;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String HEADER_TOKEN = "token";

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(CurrentLoginUser.class)
                && methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String refererHeader = nativeWebRequest.getHeader(CurrentMiniProgramMethodArgumentResolver.HEADER_REFERER);
        if (StringUtils.isEmpty(refererHeader)) {
            throw new MissingServletRequestPartException(CurrentMiniProgramMethodArgumentResolver.HEADER_REFERER);
        }
        CurrentApp currentApp = CurrentApp.fromHeader(refererHeader);

        String tokenHeader = nativeWebRequest.getHeader(HEADER_TOKEN);
        if (StringUtils.isEmpty(refererHeader)) {
            throw new MissingServletRequestPartException(CurrentMiniProgramMethodArgumentResolver.HEADER_REFERER);
        }
        CurrentLoginUser currentUser = new CurrentLoginUser(tokenHeader);
        return currentUser;
    }
}
