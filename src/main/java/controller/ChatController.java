package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/chat")
public class ChatController extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String friendId = req.getParameter("friendId");
        System.out.println("Chatting " + friendId);
        req.getSession().setAttribute("friendId", friendId);

        res.sendRedirect("app");
    }
}
