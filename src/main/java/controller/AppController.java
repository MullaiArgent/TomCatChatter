package controller;

import Service.ChatService;
import Service.RecentChatService;
import model.ChatModel;
import model.RecentChatModel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/app")
public class AppController extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        RecentChatService  recentChatService = new RecentChatService();
        ChatService chatService = new ChatService();
        String friendId = (String) req.getSession().getAttribute("friendId");
        try {
            List<RecentChatModel> recentChatModels = recentChatService.fetchRecent(req.getRemoteUser());
            req.setAttribute("recentChatModels", recentChatModels );
            if (friendId != null) {
                System.out.println("Calling chat Models");
                List<ChatModel> chatModels = chatService.fetchChats(friendId, req);
                req.setAttribute("chatModels",chatModels);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");

        rd.forward(req, res);
    }
}
