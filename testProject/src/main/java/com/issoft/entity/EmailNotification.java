package com.issoft.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: AS
 */
@Entity
@Table(name = "EMAIL_NOTIFICATIONS")
public class EmailNotification {

    private int notificationId;
    private String notification;

    @Id
    @Column(name = "NOTIFICATION_ID")
    @GeneratedValue
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    @Column(name = "NOTIFICATION")
    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailNotification that = (EmailNotification) o;

        if (notificationId != that.notificationId) return false;
        if (!notification.equals(that.notification)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = notificationId;
        result = 31 * result + notification.hashCode();
        return result;
    }
}

