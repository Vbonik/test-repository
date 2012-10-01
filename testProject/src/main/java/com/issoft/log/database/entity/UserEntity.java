package com.issoft.log.database.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

    private String userId;
    private String userpassword;
    private boolean enableflag;
    private String homedirectory;
    private boolean writepermission;
    private Long idletime;
    private Long uploadrate;
    private Long downloadrate;
    private Long maxloginnumber;
    private Long maxloginperip;
    private Long roleId;
    private String email;
    Set<EmailNotification> notifications = new HashSet<EmailNotification>();


    @Id
    @Column(name = "USERID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    @Column(name = "USERPASSWORD")
    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
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

    @Column(name = "DOWNLOADRATE")
    public Long getDownloadrate() {
        return downloadrate;
    }

    public void setDownloadrate(Long downloadrate) {
        this.downloadrate = downloadrate;
    }

    @Column(name = "ENABLEFLAG")
    public boolean isEnableflag() {
        return enableflag;
    }

    public void setEnableflag(boolean enableflag) {
        this.enableflag = enableflag;
    }

    @Column(name = "HOMEDIRECTORY")
    public String getHomedirectory() {
        return homedirectory;
    }

    public void setHomedirectory(String homedirectory) {
        this.homedirectory = homedirectory;
    }

    @Column(name = "IDLETIME")
    public Long getIdletime() {
        return idletime;
    }

    public void setIdletime(Long idletime) {
        this.idletime = idletime;
    }

    @Column(name = "MAXLOGINNUMBER")
    public Long getMaxloginnumber() {
        return maxloginnumber;
    }

    public void setMaxloginnumber(Long maxloginnumber) {
        this.maxloginnumber = maxloginnumber;
    }

    @Column(name = "MAXLOGINPERIP")
    public Long getMaxloginperip() {
        return maxloginperip;
    }

    public void setMaxloginperip(Long maxloginperip) {
        this.maxloginperip = maxloginperip;
    }

    @Column(name = "UPLOADRATE")
    public Long getUploadrate() {
        return uploadrate;
    }

    public void setUploadrate(Long uploadrate) {
        this.uploadrate = uploadrate;
    }

    @Column(name = "WRITEPERMISSION")
    public boolean isWritepermission() {
        return writepermission;
    }

    public void setWritepermission(boolean writepermission) {
        this.writepermission = writepermission;
    }
}
