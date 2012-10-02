package com.issoft.ftp.presentation.action;

import com.issoft.ftp.model.UserForm;
import com.issoft.log.database.LogEntryDAO;
import com.opensymphony.xwork2.Action;

/**
 * @author AS
 */
public class AdministrationLogAction {
    private LogEntryDAO logEntryDAO;
    private UserForm userForm;

    public String getLogList() {
        userForm.setLogEntryList(logEntryDAO.list());
        return Action.SUCCESS;
    }

    public LogEntryDAO getLogEntryDAO() {
        return logEntryDAO;
    }

    public void setLogEntryDAO(LogEntryDAO logEntryDAO) {
        this.logEntryDAO = logEntryDAO;
    }

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }

}
