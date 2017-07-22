package top.ggstar.smart.admin.autoconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import top.ggstar.smart.admin.model.SmartAdminDataSource;

import javax.sql.DataSource;

/**
 * Created by xy on 2017/7/22.
 */

@Configuration
@ConditionalOnClass(SmartAdminDataSource.class)
@EnableConfigurationProperties(SmartAdminProperties.class)
public class SmartAdminDataSourceAutoConfig {

    private final static Logger LOG = LoggerFactory.getLogger(SmartAdminDataSourceAutoConfig.class);

    @Bean
    @ConditionalOnClass(DataSource.class)
    @AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
    public SmartAdminDataSource smartAdminDataSource(@Autowired DataSource dataSource){
        SmartAdminDataSource smartAdminDataSource = new SmartAdminDataSource(dataSource);
        LOG.debug("AutoConfig SmartAdminDataSource: {}, DataSource: {}", smartAdminDataSource, dataSource);
        return smartAdminDataSource;
    }
}
