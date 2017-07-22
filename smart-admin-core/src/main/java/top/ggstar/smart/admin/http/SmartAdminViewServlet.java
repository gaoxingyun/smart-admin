package top.ggstar.smart.admin.http;

import javax.management.MBeanServerConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ggstar.smart.admin.service.SmartAdminService;
import top.ggstar.smart.admin.service.SmartAdminServiceMbean;

public class SmartAdminViewServlet extends ResourceServlet {

    private final static Logger LOG                     = LoggerFactory.getLogger(SmartAdminViewServlet.class);

    private SmartAdminServiceMbean smartAdminService            = SmartAdminService.getInstance();

    public SmartAdminViewServlet(){
        super("support/http");
    }

    /**
     * @param url 要请求的的服务地址
     * @return 调用服务后返回的json字符串
     */
    protected String process(String url) {
        String resp = smartAdminService.service(url);
        return resp;
    }
}