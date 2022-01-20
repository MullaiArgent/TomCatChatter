package controller;

import datamanagement.JDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addFriend")
public class AddFriend extends HttpServlet {
    JDBC db = new JDBC();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String friendId = req.getParameter("addFriend");

        try {
            ResultSet rs = db.dql("SELECT * FROM public.\"USERS\";");
            while (rs.next()){
                if (friendId.equals(rs.getString(1))){
                    System.out.println("friend exist "+friendId);
                    req.getSession().setAttribute("friendId",friendId);
                    RequestDispatcher rd =  req.getRequestDispatcher("addFriend.jsp");

                    rd.forward(req, res);
                    break;
                }
            }

            res.sendRedirect("app");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
