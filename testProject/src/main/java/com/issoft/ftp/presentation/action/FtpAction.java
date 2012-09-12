package com.issoft.ftp.presentation.action;

import com.issoft.ftp.client.FtpClientService;
import com.issoft.ftp.model.FTPFile;
import org.springframework.security.core.userdetails.User;
import com.issoft.ftp.util.RealPathSeparator;

import java.io.File;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import com.opensymphony.xwork2.ActionSupport;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

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
