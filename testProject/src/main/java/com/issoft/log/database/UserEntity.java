package com.issoft.log.database;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: AS
 */
@Entity
@Table(name = "USERS")

public class UserEntity {

    private Long id;
    private String userName;
    private String password;
    private boolean enabled;
    private Long roleId;
    private String email;
    Set<EmailNotification> notifications = new HashSet<EmailNotification>();

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "USERNAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ENABLED")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "ROLE_ID")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "USERS_NOTIFICATIONS",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "NOTIFICATION_ID")})
    public Set<EmailNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<EmailNotification> notifications) {
        this.notifications = notifications;
    }
}
