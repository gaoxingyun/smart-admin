package top.ggstar.smart.admin.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.ggstar.smart.admin.http.SmartAdminViewServlet;

/**
 * Created by xy on 2017/7/21.
 */
@Configuration
@ConditionalOnWebApplication()
@ConditionalOnClass(SmartAdminViewServlet.class)
@EnableConfigurationProperties(SmartAdminProperties.class)
@ConditionalOnProperty(value = "smart.admin.servlet.enable", havingValue = "true", matchIfMissing = true)
@Import(SmartAdminDataSourceAutoConfig.class)
public class SmartAdminViewServletAutoConfig {

    @Bean
    public ServletRegistrationBean smartAdminViewServlet(SmartAdminProperties config) {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new SmartAdminViewServlet(), config.getUrlPattern() != null ? config.getUrlPattern() : "/smart-admin/*");
        return registrationBean;
    }
}
