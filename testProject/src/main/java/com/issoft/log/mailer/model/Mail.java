package com.issoft.log.mailer.model;

/**
 * @author: AS
 */
public class Mail {

    private String action;
    private String filePath;
    private String userName;

    public Mail() {
    }

    public Mail(String action, String filePath, String userName) {
        this.action = action;
        this.filePath = filePath;
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
