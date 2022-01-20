package controller;

import datamanagement.JDBC;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/confirm")
public class ConfirmFriendController extends HttpServlet {

    JDBC db = new JDBC();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userId = req.getRemoteUser();
        String friendId = (String) req.getSession().getAttribute("friendId");

        if (friendId == null){
            res.sendRedirect("app");
            System.out.println("friend ID is null");
        } else {
            ResultSet friendList = null;
            try {
                friendList = db.dql("SELECT \"FRIENDS\" FROM \"USERS\" WHERE \"ID\"='" + userId + "'");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            Array friends;
            String[] friendsArray = new String[0];
            while (true) {
                try {
                    assert friendList != null;
                    if (!friendList.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (!friendList.getString(1).equals("{}")) {
                        friends = friendList.getArray(1);
                        friendsArray = (String[]) friends.getArray();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            boolean alreadyFriend = false;
            for (String s : friendsArray) {
                if (s.equals(friendId)) {
                    alreadyFriend = true;
                    break;
                }
            }
            if (!alreadyFriend) {
                try {
                    db.dml("UPDATE public.\"USERS\" set \"FRIENDS\" = array_append(\"FRIENDS\", '" + friendId + "') where \"ID\"='" + userId + "';");
                    db.dml("UPDATE public.\"USERS\" set \"FRIENDS\" = array_append(\"FRIENDS\", '" + userId + "') where \"ID\"='" + friendId + "';");
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
            res.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Expires", "0");
            res.sendRedirect("app");
        }
    }
}
