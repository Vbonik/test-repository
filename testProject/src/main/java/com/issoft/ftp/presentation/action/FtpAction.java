package com.issoft.ftp.presentation.action;

import com.issoft.ftp.client.FtpClientService;
import com.issoft.ftp.model.FTPFile;
import com.issoft.ftp.model.User;
import com.issoft.ftp.util.RealPathSeparator;

import java.io.File;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.ftpserver.ftplet.FtpException;

/**
 * @author slavabrodnitski
 */
public class FtpAction extends ActionSupport {

    private FtpClientService ftpService;
    private User user;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";

    private FTPFile ftpFile;


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
        login();
        Boolean fileUpload = ftpService.uploadFile(ftpFile.getUserFileFileName(), ftpFile.getUserFile());
        if (fileUpload) {
            return SUCCESS;
        } else return FAILURE;
    }

    public String login() {
        try {
            ftpService = new FtpClientService(Inet4Address.getByName("localhost"), 2121);
            ftpService.login(user.getLogin(), user.getPassword());
        } catch (FtpException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return SUCCESS;
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
