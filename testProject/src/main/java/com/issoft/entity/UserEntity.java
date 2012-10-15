package com.issoft.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: AS
 */
@Entity
@Table(name = "USERS")

public class UserEntity {

    private String userId;
    private String userPassword;
    private boolean enableFlag;
    private String homeDirectory;
    private boolean writePermission;
    private int idleTime;
    private int uploadRate;
    private int downloadRate;
    private int maxLoginNumber;
    private int maxLoginPerIP;
    private String email;
    private UserRole user_roles = new UserRole();
    private Set<EmailNotification> notifications = new HashSet<EmailNotification>();

    /**
     * Gets notifications ids of current user
     *
     * @return Notification id array
     */
    public int[] userNotifications() {
        int[] notificationsId = new int[notifications.size()];
        int index = 0;
        for (EmailNotification notification : notifications) {
            notificationsId[index] = notification.getNotificationId();
            index++;
        }
        return notificationsId;
    }

    @Id
    @Column(name = "USERID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    @Column(name = "USERPASSWORD")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "USERS_NOTIFICATIONS",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "NOTIFICATION_ID")})
    @Fetch(value = FetchMode.SELECT)
    public Set<EmailNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<EmailNotification> notifications) {
        this.notifications = notifications;
    }

    @Column(name = "DOWNLOADRATE")
    public int getDownloadRate() {
        return downloadRate;
    }

    public void setDownloadRate(int downloadRate) {
        this.downloadRate = downloadRate;
    }

    @Column(name = "ENABLEFLAG")
    public boolean isEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(boolean enableFlag) {
        this.enableFlag = enableFlag;
    }

    @Column(name = "HOMEDIRECTORY")
    public String getHomeDirectory() {
        return homeDirectory;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    @Column(name = "IDLETIME")
    public int getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

    @Column(name = "MAXLOGINNUMBER")
    public int getMaxLoginNumber() {
        return maxLoginNumber;
    }

    public void setMaxLoginNumber(int maxLoginNumber) {
        this.maxLoginNumber = maxLoginNumber;
    }

    @Column(name = "MAXLOGINPERIP")
    public int getMaxLoginPerIP() {
        return maxLoginPerIP;
    }

    public void setMaxLoginPerIP(int maxLoginPerIP) {
        this.maxLoginPerIP = maxLoginPerIP;
    }

    @Column(name = "UPLOADRATE")
    public int getUploadRate() {
        return uploadRate;
    }

    public void setUploadRate(int uploadRate) {
        this.uploadRate = uploadRate;
    }

    @Column(name = "WRITEPERMISSION")
    public boolean isWritePermission() {
        return writePermission;
    }

    public void setWritePermission(boolean writePermission) {
        this.writePermission = writePermission;
    }

    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @ManyToOne
    @JoinColumn(name = "role_id")
    public UserRole getUser_roles() {
        return user_roles;
    }

    public void setUser_roles(UserRole user_roles) {
        this.user_roles = user_roles;
    }

    public void resetUser() {
        resetUserId();
        resetUserPassword();
        resetHomedirectory();
        resetEnableflag();
        resetWritepermission();
        resetIdletime();
        resetUploadrate();
        resetDownloadrate();
        resetMaxloginnumber();
        resetMaxloginperip();
        resetEmail();
        resetUser_roles();
    }

    private void resetUserId() {
        this.userId = null;
    }

    private void resetUserPassword() {
        this.userPassword = null;
    }

    private void resetHomedirectory() {
        this.homeDirectory = null;
    }

    private void resetEnableflag() {
        this.enableFlag = true;
    }

    private void resetWritepermission() {
        this.writePermission = true;
    }

    private void resetIdletime() {
        this.idleTime = 0;
    }

    private void resetUploadrate() {
        this.uploadRate = 0;
    }

    private void resetDownloadrate() {
        this.downloadRate = 0;
    }

    private void resetMaxloginnumber() {
        this.maxLoginNumber = 0;
    }

    private void resetMaxloginperip() {
        this.maxLoginPerIP = 0;
    }

    private void resetEmail() {
        this.email = null;
    }

    private void resetUser_roles() {
        this.user_roles = null;
    }

    public boolean checkIsNewUser() {
        return UserRole.Role.ROLE_NEW.equals(
                UserRole.Role.valueOf(user_roles.getAuthority()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity user = (UserEntity) o;

        if (downloadRate != user.downloadRate) return false;
        if (enableFlag != user.enableFlag) return false;
        if (idleTime != user.idleTime) return false;
        if (maxLoginPerIP != user.maxLoginPerIP) return false;
        if (maxLoginNumber != user.maxLoginNumber) return false;
        if (uploadRate != user.uploadRate) return false;
        if (writePermission != user.writePermission) return false;
        if (homeDirectory != null ? !homeDirectory.equals(user.homeDirectory) : user.homeDirectory != null)
            return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (userPassword != null ? !userPassword.equals(user.userPassword) : user.userPassword != null)
            return false;
        if (user_roles != null ? !user_roles.equals(user.user_roles) : user.user_roles != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (homeDirectory != null ? homeDirectory.hashCode() : 0);
        result = 31 * result + (enableFlag ? 1 : 0);
        result = 31 * result + (writePermission ? 1 : 0);
        result = 31 * result + idleTime;
        result = 31 * result + uploadRate;
        result = 31 * result + downloadRate;
        result = 31 * result + maxLoginNumber;
        result = 31 * result + maxLoginPerIP;
        result = 31 * result + (user_roles != null ? user_roles.hashCode() : 0);
        return result;
    }
}
