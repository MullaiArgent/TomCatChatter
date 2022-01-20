package Service;


import datamanagement.JDBC;
import model.ChatModel;
import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChatService {

    public List<ChatModel> fetchChats(String friendId, HttpServletRequest req) throws SQLException, ClassNotFoundException {

        String userId = req.getRemoteUser();

        List<ChatModel> chatModels = new ArrayList<>();

        JDBC db = new JDBC();

        ResultSet rs = db.dql("SELECT * FROM \"MESSAGES\" WHERE ((\"FROM\"='"+ userId +"' OR \"FROM\"='"+ friendId +"') AND (\"TO\"='"+ friendId +"' OR \"TO\"='"+ userId +"'));"); // chat data
        // SELECT * FROM public."MESSAGES" WHERE (("FROM"='BRUCE' OR "FROM"='ALICE') AND ("TO"='BRUCE' OR "TO"='ALICE'));
        while (rs.next()){
            ChatModel chatModel = new ChatModel();
            String who;
            if (rs.getString(1).equals(userId)){
                who = "you";
            }else{
                who = rs.getString(1);
            }
            chatModel.setWho(who);
            chatModel.setTimeAndDate(rs.getString(3).substring(0,5));
            chatModel.setMessage(rs.getString(4));
            chatModel.setType(rs.getString(5));
            chatModels.add(chatModel);
            System.out.println(chatModel);

        }
        return  chatModels;
    }
}

