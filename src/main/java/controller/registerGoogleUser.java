package controller;

import datamanagement.JDBC;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/registerGoogleUser")
public class registerGoogleUser extends HttpServlet {

    JDBC db = new JDBC();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userId;
        String fullName;
        String profilePic;

        userId = req.getParameter("email");
        fullName = req.getParameter("name");
        profilePic = req.getParameter("picture");

        try {
            db.dml("INSERT INTO public.\"USERS\" VALUES ('" + userId + "','" + fullName + "','" + profilePic + "','{}');");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        res.sendRedirect("app");
    }
}
