package com.issoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class represented Notification entity
 */
@Entity
@Table(name = "email_notifications")
public class Notification {
    /**
     * Id field
     */
    @Id
    @Column(name="notification_id")
    private int id;
    /**
     * Notification value field
     */
    @Column(name="notification")
    private String notification;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

        Notification that = (Notification) o;

        if (id != that.id) return false;
        if (!notification.equals(that.notification)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + notification.hashCode();
        return result;
    }
}
