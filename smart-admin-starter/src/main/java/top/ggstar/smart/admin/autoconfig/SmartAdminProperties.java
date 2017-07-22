package top.ggstar.smart.admin.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by xy on 2017/7/21.
 */
@ConfigurationProperties("user.smart.admin.servlet")
public class SmartAdminProperties {
    private boolean enable;
    private String urlPattern;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }
}
