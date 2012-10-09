package com.issoft.ftp.presentation.action;

import com.issoft.ftp.model.UserForm;
import com.issoft.ftp.util.Constants;
import com.issoft.ftp.util.ConvertUtil;
import com.issoft.services.AdministrationService;
import com.issoft.services.NotificationService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import com.issoft.entity.Notification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Action for user profile operations
 */
public class UserProfileAction extends ActionSupport {
    /**
     * Notification service to use
     */
    private NotificationService notificationService;
    /**
     * Administration service to user
     */
    private AdministrationService administrationService;

    /**
     * User form for data managing
     */
    private UserForm userForm;

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setAdministrationService(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    @Autowired
    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }

    public UserForm getUserForm() {
        return this.userForm;
    }

    /**
     * Prepare users profile
     * Filling users notifications
     * @return SUCCESS - in case of success flow, FAILURE - otherwise
     */
    public String prepareProfile() {
        User principal =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.issoft.entity.User currentUser = administrationService.getUserById(principal.getUsername());
        userForm.setUser(currentUser);
        userForm.setCheckedNotifications(currentUser.getNotificationsId());

        List<Notification> notifications = notificationService.list();
        if (notifications != null) {
            userForm.setNotifications(notifications);
            return SUCCESS;
        }
        return Constants.FAILURE;
    }

    /**
     * Updates user notifications
     * @return SUCCESS - in case of success flow, FAILURE - otherwise
     */
    public String updateNotifications() {
        int[] checkedNotifications = userForm.getCheckedNotifications();
        List<Notification> notifications = notificationService.list();
        Map<Integer, Notification> notificationMap = new HashMap<Integer, Notification>();
        for (Notification notification : notifications) {
            notificationMap.put(notification.getId(), notification);
        }
        if (notifications == null) {
            return Constants.FAILURE;
        }

        userForm.getUser().getNotifications().clear();
        for (int index = 0; index < checkedNotifications.length; ++index) {
            userForm.getUser().getNotifications().add(notificationMap.get(checkedNotifications[index]));
        }
        administrationService.updateUser(userForm.getUser());
        return SUCCESS;
    }

    /**
     * Changes users password
     * @return SUCCESS - in case of success flow, FAILURE - otherwise
     */
    public String changePassword() {
        String oldPassword = userForm.getOldPassword();
        String newPassword = userForm.getNewPassword();
        String newPasswordConfirm = userForm.getNewPasswordConfirm();

        if (oldPassword == null || newPassword == null || !newPassword.equals(newPasswordConfirm)) {
            return Constants.FAILURE;
        }

        try {
            String oldPasswordMD5 = ConvertUtil.stringToMD5(oldPassword);
            if (oldPasswordMD5 == null || !oldPasswordMD5.equals(userForm.getUser().getUser_password())) {
                return Constants.FAILURE;
            }
        } catch (NoSuchAlgorithmException e) {
            return Constants.FAILURE;
        }

        userForm.getUser().setUser_password(newPassword);
        administrationService.updateUser(userForm.getUser());
        return SUCCESS;
    }
}
