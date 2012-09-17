package com.issoft.ftp.presentation.action;

import com.issoft.database.log.LogEntryDAO;
import com.issoft.ftp.client.FtpClientService;
import com.issoft.ftp.model.FTPFile;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import org.apache.commons.net.io.SocketInputStream;

/**
 * @author slavabrodnitski
 */

public class FtpAction extends ActionSupport {

    private FtpClientService ftpService;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private InputStream fileInputStream;
    private FTPFile ftpFile;

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

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

    public String upload() throws FtpException, UnknownHostException, IOException {
        // login();
//        ftpService = new FtpClientService();// to clean
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
//            ftpService = new FtpClientService();// to clean
            login();// to clean
            if (ftpFile.getDestination() == null) {
                ftpFile.setDestination("D:/");
            }
            fileInputStream = (InputStream) ftpService.downloadFile(ftpFile.getUserFileFileName());

        } catch (NullPointerException e) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String getDownloadFileList() throws FtpException, UnknownHostException, IOException {
        try {
//            ftpService = new FtpClientService();
            login();
            ftpFile = new FTPFile(ftpService.getAllFileNamesOnFTPServer());
        } catch (NullPointerException e) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String login() throws FtpException, IOException {
        //ftpService = new FtpClientService();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isLoggined = ftpService.login(principal.getUsername(), principal.getPassword());
        if (isLoggined) {
            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    private void ConnectToFTP() {
        //ftpService = new FtpClientService();
        ftpService.login("admin", "admin");

    }

}
