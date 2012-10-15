package com.issoft.ftp.presentation.action;

import com.issoft.ftp.model.AdministrationNotificationForm;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: nikitadavydov
 * Date: 10/11/12
 */
public class AdministrationNotificationAction {
    private static final Logger logger = Logger.getLogger(AdministrationNotificationAction.class);

    private List<AdministrationNotificationForm> listOfNotifications = new ArrayList<AdministrationNotificationForm>();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy 'в' HH:mm:ss");

    public AdministrationNotificationAction() {
    }

    public List<AdministrationNotificationForm> sendNotifications() {
        if (!listOfNotifications.isEmpty()) {
            return listOfNotifications;
        }
        return Collections.emptyList();
    }

    public void createNotification(String name, String activity, String status) {
        AdministrationNotificationForm notificationForm = new AdministrationNotificationForm();
        notificationForm.setUser(name);
        notificationForm.setActivity(activity);
        notificationForm.setActivityStatus(status);
        notificationForm.setDateTime(simpleDateFormat.format(new Date()));
        addNotificationForm(notificationForm);
    }

    private void addNotificationForm(AdministrationNotificationForm form) {
        listOfNotifications.add(form);
    }

    public void emptyListOfNotifications() {
        listOfNotifications.clear();
    }

    //setter
    public List<AdministrationNotificationForm> getListOfNotifications() {
        return listOfNotifications;
    }

    public void setListOfNotifications(List<AdministrationNotificationForm> listOfNotifications) {
        this.listOfNotifications = listOfNotifications;
    }
}
