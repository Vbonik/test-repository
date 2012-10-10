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
    private Set<UserEntity> users = new HashSet<UserEntity>();

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

    @ManyToMany(mappedBy = "notifications")
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
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

