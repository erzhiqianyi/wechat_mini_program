package top.erzhiqian.wechat.core.spring.resolver.request;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import top.erzhiqian.wechat.core.exception.BaseException;
import top.erzhiqian.wechat.core.exception.BaseExceptionCode;
import top.erzhiqian.wechat.core.spring.config.ApplicationContextHolder;

import java.util.Optional;

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

        String token = nativeWebRequest.getHeader(HEADER_TOKEN);
        if (StringUtils.isEmpty(token)) {
            throw new MissingServletRequestPartException(CurrentMiniProgramMethodArgumentResolver.HEADER_REFERER);
        }
        LoadCurrentUserService userService = ApplicationContextHolder.getBean(LoadCurrentUserService.class);
        Optional<CurrentLoginUser> optional = userService.loadUser(token);
        optional.orElseThrow(() -> new BaseException(BaseExceptionCode.TOKEN_EXPIRED, "登录失败，请重新登录。"));
        CurrentLoginUser currentLoginUser = optional.get();
        currentLoginUser.setCurrentApp(currentApp);
        return currentLoginUser;
    }
}
