package collection.model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private String text;
    private User user;
    private Date date;

    public Comment(String text, User user, Date date) {
        this.text = text;
        this.user = user;
        this.date = date;
    }

    public Comment() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "text='" + text + '\'' +
                ", user=" + user +
                ", date=" + date +
                '}';
    }

}
