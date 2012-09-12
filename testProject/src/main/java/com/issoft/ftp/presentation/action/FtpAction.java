package com.issoft.ftp.presentation.action;

import com.issoft.ftp.client.FtpClientService;
import com.issoft.ftp.model.FTPFile;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author slavabrodnitski
 */
public class FtpAction extends ActionSupport {

    private FtpClientService ftpService;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private FTPFile ftpFile;

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

    @Override
    public String execute() {
        return SUCCESS;
    }

    public String upload() {
        connectToFTP();
        try {
            Boolean fileUpload = ftpService.uploadFile(ftpFile.getUserFileFileName(), ftpFile.getUserFile());
        } catch (NullPointerException e) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String download() {
        connectToFTP();
        try {
            if (ftpFile.getDestination() == null) {
                ftpFile.setDestination("C:/");
            }
            File file = ftpService.downloadFile(ftpFile.getUserFileFileName(), ftpFile.getDestination() + ftpFile.getUserFileFileName());
        } catch (NullPointerException e) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String getDownloadFileList() throws FtpException, IOException {
        connectToFTP();
        try {
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
            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    private void connectToFTP() {
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
