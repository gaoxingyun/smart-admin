package top.ggstar.smart.admin.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ggstar.smart.admin.common.util.StringUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xy on 2017/7/22.
 */
public class SmartAdminService implements SmartAdminServiceMbean{

    private final static Logger LOG = LoggerFactory.getLogger(SmartAdminService.class);

    private final static SmartAdminService instance               = new SmartAdminService();

    public final static int               RESULT_CODE_SUCCESS    = 0;
    public final static int               RESULT_CODE_ERROR      = -1;
    public final static String            RESULT_MSG_UNSUPPORT   = "Do not support this request";

    public static SmartAdminService getInstance() {
        return instance;
    }


    public String service(String method, String url, String body) {

        Map<String, String> parameters = getParameters(url);
        Object json = null;
        LOG.debug("SmartAdminService service method param url: {}", url);
        try {
            if (url.startsWith("/sql/query.json")) {
                String sql = JSON.parseObject(body).getString("sql");
                json = new SqlService().execQuerySql(sql);
                LOG.debug("exec query sql result: {}", json);
                String result = returnJSONResult(RESULT_CODE_SUCCESS, json);
                LOG.debug("query result: {}", result);
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return returnJSONResult(RESULT_CODE_ERROR, RESULT_MSG_UNSUPPORT);
        }
        return null;
    }



    public static Map<String, String> getParameters(String url) {
        if (url == null || (url = url.trim()).length() == 0) {
            return Collections.<String, String> emptyMap();
        }

        String parametersStr = StringUtils.subString(url, "?", null);
        if (parametersStr == null || parametersStr.length() == 0) {
            return Collections.<String, String> emptyMap();
        }

        String[] parametersArray = parametersStr.split("&");
        Map<String, String> parameters = new LinkedHashMap<String, String>();

        for (String parameterStr : parametersArray) {
            int index = parameterStr.indexOf("=");
            if (index <= 0) {
                continue;
            }

            String name = parameterStr.substring(0, index);
            String value = parameterStr.substring(index + 1);
            parameters.put(name, value);
        }
        return parameters;
    }

    public static String returnJSONResult(int resultCode, Object content) {
        Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
        dataMap.put("result_code", resultCode);
        dataMap.put("content", content);
        return JSON.toJSONString(dataMap);
    }
}
