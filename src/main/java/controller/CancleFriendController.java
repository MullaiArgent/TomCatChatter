package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/cancel_friend")
public class CancleFriendController extends HttpServlet {
    public void service (HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.getSession().removeAttribute("friendId");
        req.getSession().removeAttribute("friendImage");
        res.sendRedirect("app");
    }
}
