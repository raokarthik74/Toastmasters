package com.karthikravindrarao.ToastDirector.entities;

/**
 * Created by Karthik on 09/10/15.
 */
public class NotificationPreparer {
    private String title;
    private String alert;
    private String url;
    private String dateAndTime;

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
