package top.jiangyixiong.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.jiangyixiong.domain.User;
import top.jiangyixiong.util.JdbcUtils;

/**
 * DemoProjects
 * Login
 *
 * @author Yixiong J
 * @version 2019/12/8 14:45
 */
public class Login {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDs());
    public User login(User user){
        User loginUser = null;
        try {
            String sql="SELECT * FROM users WHERE uname = ? AND upwd = MD5(?)";
            loginUser = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUname(), user.getUpwd());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return loginUser;
    }
}
