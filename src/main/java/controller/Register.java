package controller;

import datamanagement.JDBC;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/register")
public class Register extends HttpServlet {

    JDBC db = new JDBC();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userId;
        String fullName;
        String profilePic;
        String password;
        String re_password;

        userId = req.getParameter("userid");
        fullName = req.getParameter("full_name");
        profilePic = req.getParameter("profile_pic");
        password = req.getParameter("password");
        re_password = req.getParameter("re_password");
        if (password.equals(re_password)) {
            try {
                db.dml("INSERT INTO public.\"USERS\" VALUES ('" + userId + "','" + fullName + "','" + profilePic + "','{}');");
                db.addUser("INSERT INTO users VALUES ('" + userId + "','" + password + "')");
                db.addUser("INSERT INTO users_roles VALUES ('" + userId + "','user')");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            res.sendRedirect("app");
        }else{
            res.sendRedirect("adduser");
        }
    }
}
