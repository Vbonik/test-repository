package com.issoft.ftp.presentation.action;

import com.issoft.database.log.LogEntryDAO;
import com.issoft.ftp.client.FtpClientService;
import com.issoft.ftp.model.FTPFile;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * @author slavabrodnitski
 */
public class FtpAction extends ActionSupport {

    private FtpClientService ftpService;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private FTPFile ftpFile;
    private LogEntryDAO logEntryDAO;

    public FtpClientService getFtpService() {
        return ftpService;
    }

    public void setFtpService(FtpClientService ftpService) {
        this.ftpService = ftpService;
    }

    public FTPFile getFtpFile() {
        return ftpFile;
    }

    public void setFtpFile(FTPFile ftpFile) {
        this.ftpFile = ftpFile;
    }

    public LogEntryDAO getLogEntryDAO() {
        return logEntryDAO;
    }

    public void setLogEntryDAO(LogEntryDAO logEntryDAO) {
        this.logEntryDAO = logEntryDAO;
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public String upload() throws FtpException, UnknownHostException, IOException {
        // login();
        ftpService = new FtpClientService();// to clean
        login();// to clean
        Boolean fileUpload = ftpService.uploadFile(ftpFile.getUserFileFileName(), ftpFile.getUserFile());
        if (fileUpload) {
            return SUCCESS;
        } else {
            return FAILURE;
        }

    }

    public String download() throws FtpException, UnknownHostException, IOException {
        try {
            ftpService = new FtpClientService();// to clean
            login();// to clean
            if (ftpFile.getDestination() == null) {
                ftpFile.setDestination("D:/");
            }
            File file = ftpService.downloadFile(ftpFile.getUserFileFileName(), ftpFile.getDestination() + ftpFile.getUserFileFileName());
        } catch (NullPointerException e) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String getDownloadFileList() throws FtpException, UnknownHostException, IOException {
        try {
            ftpService = new FtpClientService();
            login();
            ftpFile = new FTPFile(ftpService.getAllFileNamesOnFTPServer());
        } catch (NullPointerException e) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String login() throws FtpException, UnknownHostException, IOException {
        //will change in spring config
        ftpService = new FtpClientService();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isLoggined = ftpService.login(principal.getUsername(), principal.getPassword());
        if (isLoggined) {
            audit(principal, "login", SUCCESS);
            return SUCCESS;
        } else {
            audit(principal, "login", FAILURE);
            return FAILURE;
        }
    }

    private void ConnectToFTP() {
        //TODO: ex!
        try {
            ftpService = new FtpClientService();
            ftpService.login("admin", "admin");
        } catch (FtpException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * Writes executed action details to database. TEMP? Bug with creating bean
     * property.
     *
     * @param user
     * @param action
     * @param status
     */
    private void audit(User user, String action, String status) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-hibernate.xml");
        logEntryDAO = (LogEntryDAO) context.getBean("myEntryDAO");
        logEntryDAO.saveEntry(user, action, status);
    }
}
