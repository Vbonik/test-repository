package com.issoft.ftp.model;


/**
 * User: nikitadavydov
 * Date: 10/11/12
 */
public class AdministrationNotificationForm {
    private String activity;
    private String user;
    private String dateTime;
    private String activityStatus;

    public AdministrationNotificationForm() {
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministrationNotificationForm that = (AdministrationNotificationForm) o;

        if (activity != null ? !activity.equals(that.activity) : that.activity != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (activityStatus != null ? !activityStatus.equals(that.activityStatus) : that.activityStatus != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = activity != null ? activity.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (activityStatus != null ? activityStatus.hashCode() : 0);
        return result;
    }
}
