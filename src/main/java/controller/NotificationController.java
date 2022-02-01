package controller;

import Service.NotificationService;
import model.NotificationModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/notification")
public class NotificationController extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        NotificationService notificationService = new NotificationService();
        RequestDispatcher rd = req.getRequestDispatcher("notification.jsp");
        try {
            List<NotificationModel> notificationModels = notificationService.fetchNotification(req);
            req.setAttribute("notificationModels",notificationModels);
            req.setAttribute("count",""+notificationModels.size());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        rd.forward(req, res);
    }
}
