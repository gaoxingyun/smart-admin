package top.ggstar.smart.admin.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

/**
 * 数据源，需外部注入
 */
public class SmartAdminDataSource {


    private final static Logger LOG = LoggerFactory.getLogger(SmartAdminDataSource.class);

    private final static SmartAdminDataSource instance = new SmartAdminDataSource();

    private static DataSource dataSource = null;

    private SmartAdminDataSource() {
    }

    public SmartAdminDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        LOG.debug("SmartAdminDataSource init DataSource Success, DataSource: {}", this.dataSource);
    }

    public static SmartAdminDataSource getInstance()
    {
        return instance;
    }

    public  DataSource getDataSource() {
        return this.dataSource;
    }
}
