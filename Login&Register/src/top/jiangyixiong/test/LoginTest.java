package top.jiangyixiong.test;

import org.junit.jupiter.api.Test;
import top.jiangyixiong.dao.Login;
import top.jiangyixiong.domain.User;

/**
 * DemoProjects
 * LoginTest
 *
 * @author Yixiong J
 * @version 2019/12/8 15:51
 */
public class LoginTest {
    @Test
    public void testLogin(){
        User userLogin = new User("gary","di2tiancai");
        User user = new Login().login(userLogin);
        System.out.println(user);
    }
}
