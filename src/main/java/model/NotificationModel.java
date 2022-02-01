package model;

public class NotificationModel {
    private String RecipientId;
    private String SenderId;
    private String ActivityType;
    private String TimeStamp;
    private boolean isRead;

    @Override
    public String toString() {
        return "NotificationModel{" +
                "RecipientId='" + RecipientId + '\'' +
                ", SenderId='" + SenderId + '\'' +
                ", ActivityType='" + ActivityType + '\'' +
                ", TimeStamp='" + TimeStamp + '\'' +
                ", isRead=" + isRead +
                '}';
    }

    public String getRecipientId() {
        return RecipientId;
    }

    public void setRecipientId(String recipientId) {
        RecipientId = recipientId;
    }

    public String getSenderId() {
        return SenderId;
    }

    public void setSenderId(String senderId) {
        SenderId = senderId;
    }

    public String getActivityType() {
        return ActivityType;
    }

    public void setActivityType(String activityType) {
        ActivityType = activityType;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
