package model;

public class RecentChatModel {
    String friendId;
    String fullName;
    String profile;
    String lastMessage;
    String lastDate;


    @Override
    public String toString() {
        return "RecentChatModel{" +
                "friendId='" + friendId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", profile='" + profile + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastDate='" + lastDate + '\'' +
                '}';
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}


