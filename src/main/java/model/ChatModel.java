package model;


public class ChatModel {
    private String who;
    private String timeAndDate;
    private String message;
    private String type;

    @Override
    public String toString() {
        return "ChatModel{" +
                "who='" + who + '\'' +
                ", timeAndDate='" + timeAndDate + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

