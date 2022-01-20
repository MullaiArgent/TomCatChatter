package Service;

import datamanagement.JDBC;
import model.RecentChatModel;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecentChatService {

    List<RecentChatModel> recentChatModels = new ArrayList<>();
    public List<RecentChatModel> getRecentChatModels() {
        return recentChatModels;
    }
    public List<RecentChatModel> fetchRecent(String userId) throws SQLException, ClassNotFoundException {
        JDBC db = new JDBC();
        List<RecentChatModel> recentChatModels = new ArrayList<>();
        ResultSet friendList = db.dql("SELECT \"FRIENDS\" FROM \"USERS\" WHERE \"ID\"='" + userId + "' ");
        Array friends;
        String[] friendsArray = new String[0];
        while(friendList.next()){
            if (!friendList.getString(1).equals("{}")){
                friends = friendList.getArray(1);
                friendsArray = (String[])friends.getArray();
            }
        }
        for (String friendId : friendsArray) {// array of friends
            String profile = null;
            String fullName = null;
            String lastMessage = "Say \"Hello\"";
            String lastDate = "NEW CHAT";
            ResultSet rs = db.dql("SELECT \"PROFILEPIC\",\"FULLNAME\" FROM \"USERS\" WHERE \"ID\" = '" + friendId.trim() + "'");
            // SELECT "PROFILEPIC","FULLNAME" FROM "USERS" WHERE "USERID"='mullai';
            while (rs.next()) {
                fullName = rs.getString(2);
                if (rs.getString(1).equals("")) {
                    profile = "https://cdn.onlinewebfonts.com/svg/img_131793.png";
                } else {
                    profile = rs.getString(1);
                }
            }
            ResultSet rs1 = db.dql("SELECT * FROM \"MESSAGES\" WHERE ((\"FROM\"='" + userId + "' OR \"FROM\"='" + friendId + "') AND (\"TO\"='" + friendId + "' OR \"TO\"='" + userId + "'));");
            while (rs1.next()) {
                lastMessage = rs1.getString(1) + " : " + rs1.getString(4);
                lastDate = rs1.getString(3).substring(0, 5);
            }
            RecentChatModel recentChatModel = new RecentChatModel();
            recentChatModel.setFriendId(friendId);
            recentChatModel.setProfile(profile);
            recentChatModel.setLastMessage(lastMessage);
            recentChatModel.setLastDate(lastDate);
            recentChatModel.setFullName(fullName);
            recentChatModels.add(recentChatModel);
        }
        this.recentChatModels = recentChatModels;
        return this.getRecentChatModels();
    }
}

