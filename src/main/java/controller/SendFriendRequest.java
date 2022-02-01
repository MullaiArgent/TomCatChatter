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

@WebServlet(urlPatterns = "/sendFriendRequest")
public class SendFriendRequest extends HttpServlet {
    JDBC db = new JDBC();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String friendId = req.getParameter("addFriend");
        try {
            ResultSet rs = db.dql("SELECT * FROM public.\"USERS\";");
            while (rs.next()){
                if (friendId.equals(rs.getString(1))){
                    System.out.println("friend exist "+friendId); // TODO check if the requested is is already a friend
                    req.getSession().setAttribute("friendId",friendId);
                    req.getSession().setAttribute("friendImage",rs.getString(3));
                    RequestDispatcher rd =  req.getRequestDispatcher("sendFriendRequest.jsp");
                    rd.forward(req, res);
                    break;
                }
            }

            // TODO send a mail to join in the application

            res.sendRedirect("app");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
