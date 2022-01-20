package controller;


import datamanagement.JDBC;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/send_message")
public class SendMessage extends HttpServlet {
    JDBC db = new JDBC();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String userId = req.getRemoteUser();
        String friendId = (String) req.getSession().getAttribute("friendId");
        String message = req.getParameter("message");
        if (message != null){
        try {
            db.dml("INSERT INTO \"MESSAGES\" VALUES ('"+ userId +"','"+ friendId +"',now(),'" + message + "','chat');");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }}
        res.sendRedirect("app");
    }
}
