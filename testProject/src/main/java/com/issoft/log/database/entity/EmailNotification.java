package com.issoft.log.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
}

