package top.ggstar.smart.admin.service;

import top.ggstar.smart.admin.model.SmartAdminDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * sql数据库服务
 * Created by xy on 2017/7/22.
 */
public class SqlService {

    public List<Map<String, Object>> execQuerySql(String sql) throws SQLException {
        DataSource dataSource = SmartAdminDataSource.getInstance().getDataSource();
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            ResultSetMetaData md = resultSet.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (resultSet.next()) {
                Map<String,Object> rowData = new LinkedHashMap<String,Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), resultSet.getObject(i));
                }
                list.add(rowData);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            try {
            if (statement != null) {
                statement.close();
            }
            if (statement != null) {
                connection.close();
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
