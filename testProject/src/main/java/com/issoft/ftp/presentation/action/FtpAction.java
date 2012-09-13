package com.issoft.ftp.presentation.action;

import com.issoft.ftp.client.FtpClientService;
import com.issoft.database.log.EntryDAO;
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

<<<<<<< Updated upstream
    private FtpClientService ftpService;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private FTPFile ftpFile;
=======
    private FtpClientService ftpService ;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private FTPFile ftpFile;

   
>>>>>>> Stashed changes

    
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

    public EntryDAO getEntryDAO() {
        return entryDAO;
    }

    public void setEntryDAO(EntryDAO entryDAO) {
        this.entryDAO = entryDAO;
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

<<<<<<< Updated upstream
    public String upload() {
        connectToFTP();
        try {
            Boolean fileUpload = ftpService.uploadFile(ftpFile.getUserFileFileName(), ftpFile.getUserFile());
        } catch (NullPointerException e) {
=======
    public String upload() throws FtpException, UnknownHostException {
        // login();
        ftpService = new FtpClientService();
        Boolean fileUpload = ftpService.uploadFile(ftpFile.getUserFileFileName(), ftpFile.getUserFile());
        if (fileUpload) {
            return SUCCESS;
        } else {
>>>>>>> Stashed changes
            return FAILURE;
        }
        return SUCCESS;
    }

<<<<<<< Updated upstream
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
=======
    public String getDownloadFileList() throws FtpException, UnknownHostException, IOException {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
            audit(principal, "login", SUCCESS);
=======
>>>>>>> Stashed changes
            return SUCCESS;
        }
        else {
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
     * Writes executed action details to database.
     * TEMP? Bug with creating bean property.
     *
     * @param user
     * @param action
     * @param status
     */
    private void audit(User user, String action, String status) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernateSettingsTemp.xml");
        entryDAO = (EntryDAO) context.getBean("myEntryDAO");
        entryDAO.saveEntry(user, action, status);
    }
}
