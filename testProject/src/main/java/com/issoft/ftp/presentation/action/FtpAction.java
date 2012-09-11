package com.issoft.ftp.presentation.action;

import com.issoft.ftp.client.FtpClientService;
import com.issoft.ftp.model.FTPFile;
import com.issoft.ftp.model.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.ftpserver.ftplet.FtpException;

import java.io.File;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author slavabrodnitski
 */
public class FtpAction extends ActionSupport {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";

    private User user;
    private FTPFile ftpFile;
    private FtpClientService ftpService;

    @Override
    public String execute() {
        return SUCCESS;
    }

    public String upload() {
        //TODO: need delete or make login() IoC!
        //or use SpringSecurity login details

        //EXCEPTION: if upload is not working e.g.:
        //No result defined for action com.issoft.ftp.presentation.action.FtpAction and result input
        //this mean that file size is too big!
        login();
        Boolean fileUpload = false;
        try {
            fileUpload = ftpService.uploadFile(ftpFile.getUserFileFileName(), ftpFile.getUserFile());
        } catch (NullPointerException ex) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String getDownloadFileList() {
        login();
        try {
            ftpFile = new FTPFile(ftpService.getAllFilesInFTPServer());
        } catch (NullPointerException e) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String download() {
        login();
        File file = null;
        try {
            file = ftpService.downloadFile(ftpFile.getUserFileFileName(), ftpFile.getDestination() + ftpFile.getUserFileFileName());
        } catch (NullPointerException ex) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String login() {
        try {
            ftpService = new FtpClientService(Inet4Address.getByName("localhost"), 2121);
            if (user != null) {
                ftpService.login(user.getLogin(), user.getPassword());
            } else {
                ftpService.login("admin", "admin");
            }
        } catch (FtpException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FTPFile getFtpFile() {
        return ftpFile;
    }

    public void setFtpFile(FTPFile ftpFile) {
        this.ftpFile = ftpFile;
    }

    public FtpClientService getFtpService() {
        return ftpService;
    }

    public void setFtpService(FtpClientService ftpService) {
        this.ftpService = ftpService;
    }
}
