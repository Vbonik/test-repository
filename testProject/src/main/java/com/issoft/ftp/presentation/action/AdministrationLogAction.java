package com.issoft.ftp.presentation.action;

import com.issoft.ftp.model.AdministrationForm;
import com.issoft.log.database.LogEntryDAO;
import com.opensymphony.xwork2.Action;

/**
 * @author AS
 */
public class AdministrationLogAction {
    private LogEntryDAO logEntryDAO;
    private AdministrationForm administrationForm;

    public String getLogList() {
        administrationForm.setLogEntryList(logEntryDAO.list());
        return Action.SUCCESS;
    }

    public LogEntryDAO getLogEntryDAO() {
        return logEntryDAO;
    }

    public void setLogEntryDAO(LogEntryDAO logEntryDAO) {
        this.logEntryDAO = logEntryDAO;
    }

    public AdministrationForm getAdministrationForm() {
        return administrationForm;
    }

    public void setAdministrationForm(AdministrationForm administrationForm) {
        this.administrationForm = administrationForm;
    }

}
