import com.alibaba.druid.pool.DruidDataSourceFactory;

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
import java.util.Properties;

@WebServlet(name = "login",urlPatterns = "/be/log")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        Properties prop=new Properties();
        InputStream inputStream = Login.class.getClassLoader().getResourceAsStream("druid.properties");
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            assert inputStream != null;
            prop.load(inputStream);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            conn = dataSource.getConnection();
            String sql = "SELECT * FROM users WHERE uname = ? AND upwd = MD5(?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,pwd);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {System.out.println("yep");}
            else {System.out.println("nope");}
        } catch (Exception e) {
            e.printStackTrace();
        }
      finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
