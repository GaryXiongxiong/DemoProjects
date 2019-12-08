package top.jiangyixiong.web;

import top.jiangyixiong.dao.Login;
import top.jiangyixiong.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "be/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        User userLogin = new User(name,pwd);
        User user = new Login().login(userLogin);
        if(user==null){
            request.getRequestDispatcher("failLogin").forward(request,response);
        }
        else{
            request.setAttribute("user",user);
            request.getRequestDispatcher("successLogin").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
