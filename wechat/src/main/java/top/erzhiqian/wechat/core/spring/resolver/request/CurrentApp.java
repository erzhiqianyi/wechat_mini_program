package top.erzhiqian.wechat.core.spring.resolver.request;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class CurrentApp {
    private static final String SITE_END = ".com";
    private static final String PATH_PRARAM_SPLITE = "/";
    private String appId;
    private String version;

    public CurrentApp(String appId, String version) {
        this.appId = appId;
        this.version = version;
    }

    private CurrentApp(String header) {
        if (StringUtils.isEmpty(header)) {
            throw new IllegalArgumentException("header不能为空。");
        }
        int siteIndex = header.indexOf(SITE_END);
        if (siteIndex < 0) {
            throw new IllegalArgumentException("header url格式不正确。");
        }
        header = header.substring(siteIndex + SITE_END.length(), header.length());
        String[] array = header.split(PATH_PRARAM_SPLITE);
        if (array.length != 4){
            throw new IllegalArgumentException("header url参数错误不正确。");
        }
        this.appId = array[1];
        this.version = array[2];
    }

    public static CurrentApp fromHeader(String header) {
        if (StringUtils.isEmpty(header)) {
            return null;
        }
        CurrentApp currentApp = new CurrentApp(header);
        return currentApp;
    }
}
