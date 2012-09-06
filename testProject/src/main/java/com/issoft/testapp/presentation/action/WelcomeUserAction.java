package com.issoft.testapp.presentation.action;

import com.issoft.testapp.ftp.client.FtpClientService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.ftpserver.ftplet.FtpException;

/**
 *
 * @author slavabrodnitski
 */
public class WelcomeUserAction implements Action{

    private FtpClientService ftpService;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // all struts logic here
    @Override
    public String execute() throws UnknownHostException{
        try {
            ftpService = new FtpClientService(Inet4Address.getByName("localhost"), 2121);
            ftpService.login("admin", "admin");
        } catch(FtpException fEx) {
            
        } catch(UnknownHostException uhEx) {
            
        }
        File file = ftpService.downloadFile("nick.torrent", "d:/ttt.torrent");       
        return "SUCCESS";

    }
}
