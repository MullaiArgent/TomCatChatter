package Service;

import datamanagement.JDBC;
import model.NotificationModel;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    public List<NotificationModel> fetchNotification (HttpServletRequest req) throws SQLException, ClassNotFoundException {

        String userId = req.getRemoteUser();
        System.out.println(userId);

        List<NotificationModel> notificationModels = new ArrayList<>();

        JDBC db = new JDBC();

        ResultSet rs = db.dql("SELECT * FROM public.\"NOTIFICATION\" WHERE \"RECIPIENT_ID\"='"+userId+"';");

        while(rs.next()){
            NotificationModel notificationModel = new NotificationModel();
            notificationModel.setRecipientId(userId);
            notificationModel.setSenderId(rs.getString(2));
            notificationModel.setActivityType(rs.getString(3));
            notificationModel.setTimeStamp(rs.getString(4));
            notificationModel.setRead(rs.getBoolean(5));
            notificationModels.add(notificationModel);
        }

        return notificationModels;

    }
}
