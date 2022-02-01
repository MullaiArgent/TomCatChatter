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

@WebServlet(urlPatterns = "/rejectIncomingFriendRequest")
public class RejectIncomingFriendRequestController extends HttpServlet {
    JDBC db = new JDBC();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String userId = req.getRemoteUser();
        String friendId = req.getParameter("friendId");
        System.out.println(friendId + "friend from the reject");
        if (friendId == null){
            res.sendRedirect("app");
            System.out.println("friend ID is null");
        } else {
            try {
                db.dml("delete from public.\"NOTIFICATION\" where \"RECIPIENT_ID\"='" + userId + "' and \"SENDER_ID\"='" + friendId + "';");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher rd = req.getRequestDispatcher("notification");
        rd.forward(req, res);
    }
}
