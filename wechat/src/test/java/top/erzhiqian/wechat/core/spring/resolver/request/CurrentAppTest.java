package top.erzhiqian.wechat.core.spring.resolver.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrentAppTest {

    @Test
    public void fromHeader() {
        String header = "https://servicewechat.com/wx9cc422768a1fb65f/devtools/page-frame.html";
        CurrentApp app = CurrentApp.fromHeader(header);
        String appId = "wx9cc422768a1fb65f";
        String version = "devtools";
        assertEquals(appId,app.getAppId());
        assertEquals(version,app.getVersion());
    }
}