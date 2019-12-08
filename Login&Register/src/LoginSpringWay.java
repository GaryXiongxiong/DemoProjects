import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@WebServlet(name = "loginSpringWay",urlPatterns = "/be/login")
public class LoginSpringWay extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        Properties prop=new Properties();
        InputStream inputStream = LoginSpringWay.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            assert inputStream != null;
            prop.load(inputStream);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            JdbcTemplate jt = new JdbcTemplate(dataSource);
            String sql = "SELECT * FROM users WHERE uname = ? AND upwd = MD5(?)";
            List<Map<String, Object>> result = jt.queryForList(sql, name, pwd);
            if(!result.isEmpty()) {System.out.println("yep");}
            else {System.out.println("nope");}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
