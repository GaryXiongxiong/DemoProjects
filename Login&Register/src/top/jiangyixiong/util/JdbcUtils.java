package top.jiangyixiong.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DemoProjects
 * JdbcUtils
 *
 * @author Yixiong J
 * @version 2019/12/8 14:31
 */
public class JdbcUtils {
    private static DataSource ds;
    static{
        try {
            Properties prop = new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            prop.load(inputStream);
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDs() {
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
