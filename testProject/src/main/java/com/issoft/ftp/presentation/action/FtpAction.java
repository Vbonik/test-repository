package com.issoft.ftp.presentation.action;

import com.issoft.ftp.client.FtpClientService;
import com.issoft.ftp.model.FTPFile;
import org.springframework.security.core.userdetails.User;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;

import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author slavabrodnitski
 */
public class FtpAction extends ActionSupport {

    private FtpClientService ftpService;
    private User user;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private FTPFile ftpFile;
    private String j_username;
    private String j_password;

    public String getJ_password() {
        return j_password;
    }

    public void setJ_password(String j_password) {
        this.j_password = j_password;
    }

    public String getJ_username() {
        return j_username;
    }

    public void setJ_username(String j_username) {
        this.j_username = j_username;
    }

    public FtpClientService getFtpService() {
        return ftpService;
    }

    public void setFtpService(FtpClientService ftpService) {
        this.ftpService = ftpService;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public FTPFile getFtpFile() {
        return ftpFile;
    }

    public void setFtpFile(FTPFile ftpFile) {
        this.ftpFile = ftpFile;
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public String upload() {
        // login();
        Boolean fileUpload = ftpService.uploadFile(ftpFile.getUserFileFileName(), ftpFile.getUserFile());
        if (fileUpload) {
            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    public String getDownloadFileList() {
        //login();
        try {
            ftpFile = new FTPFile(ftpService.getAllFileNamesOnFTPServer());
        } catch (NullPointerException e) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String login() throws FtpException, UnknownHostException,IOException {
        //will change in spring config
        ftpService = new FtpClientService();
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isLoggined = ftpService.login(principal.getUsername(), principal.getPassword());
        if (isLoggined) {            
            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    private void ConnectToFTP() {
        //TODO: ex!
        try {
            ftpService = new FtpClientService(Inet4Address.getByName("localhost"), 2121);
            ftpService.login("admin", "admin");
        } catch (FtpException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
