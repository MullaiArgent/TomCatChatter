package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/invitation")
public class InvitationHandler extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String friendId = req.getParameter("invited");
        System.out.println(friendId + " from invitation handler");
        if (friendId == null){
            res.sendRedirect("app");
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("friendId", friendId);
            res.sendRedirect("adduser");
        }

    }
}
