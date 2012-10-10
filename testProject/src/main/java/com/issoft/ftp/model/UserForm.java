package com.issoft.ftp.model;

import com.issoft.entity.EmailNotification;
import com.issoft.entity.UserEntity;

import java.util.List;

/**
 * Form for user profile operations
 */
public class UserForm {
    /**
     * Notifications list
     */
    private List<EmailNotification> notifications;
    /**
     * Selected by user notification ids
     */
    private int[] checkedNotifications;

    /**
     * Users old password to check
     */
    private String oldPassword;
    /**
     * Users new password to set
     */
    private String newPassword;
    /**
     * Users new password confirmation
     */
    private String newPasswordConfirm;

    /**
     * Current user
     */
    private UserEntity user;

    public List<EmailNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<EmailNotification> notifications) {
        this.notifications = notifications;
    }

    public int[] getCheckedNotifications() {
        return checkedNotifications;
    }

    public void setCheckedNotifications(int[] checkedNotifications) {
        this.checkedNotifications = checkedNotifications;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
}
