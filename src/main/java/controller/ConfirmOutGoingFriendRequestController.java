package controller;

import datamanagement.JDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/confirmOutGoingFriendRequest")
public class ConfirmOutGoingFriendRequestController extends HttpServlet {
    JDBC db = new JDBC();
    @Override
    public void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String friendId = (String) req.getSession().getAttribute("friendId");
        req.getSession().removeAttribute("friendId");
        String userId = req.getRemoteUser();
        try {
            db.dml("insert into public.\"NOTIFICATION\" values('"+ friendId +"','"+ userId +"','friend_request',now(),FALSE);");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = req.getRequestDispatcher("app");
        rd.forward(req, res);
    }
}
