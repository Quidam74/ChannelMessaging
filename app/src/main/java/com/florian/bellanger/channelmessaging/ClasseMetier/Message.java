package com.florian.bellanger.channelmessaging.ClasseMetier;

import java.util.Date;

/**
 * Created by bellangf on 05/02/2018.
 */
public class Message {
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    private int userID;
    private String message;
    private String date;
    private String imageUrl;

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", userID=" + userID +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public Message(int userID, String username, String message, String date, String imageUrl) {
        this.setUserID(userID);
        this.setUsername(username);
        this.setImageUrl(imageUrl);
        this.setDate(date);
        this.setMessage(message);
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getUserID() {

        return userID;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
