package com.issoft.ftp.presentation.action;

import com.issoft.ftp.client.FtpClientService;
import com.issoft.ftp.model.User;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import org.apache.ftpserver.ftplet.FtpException;

/**
 *
 * @author slavabrodnitski
 */
public class FtpAction implements Action{

    private FtpClientService ftpService;
    private User user;
   

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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public FtpClientService getFtpService() {
        return ftpService;
    }

    public void setFtpService(FtpClientService ftpService) {
        this.ftpService = ftpService;
    }
    
    
}
