/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.ftp.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.log4j.Logger;

/**
 *
 * @author slavabrodnitski
 */
public class FtpClientService {

    private static final Integer DEFAULT_FTP_LISTENER_PORT  = 2121;
    private static final Logger logger = Logger.getLogger(FtpClientService.class);
    
    
    private final FTPClient client = new FTPClient();
    private final InetAddress host;
    private final Integer port;
    private Boolean isLoggined = false;

    public FtpClientService() throws UnknownHostException, FtpException {
        this(InetAddress.getByName("localhost"), DEFAULT_FTP_LISTENER_PORT);
    }

    public FtpClientService(InetAddress host, Integer port) throws FtpException {
        if (host != null) {
            this.host = host;
            try {
                this.port = port;
                if (port != null) {
                    client.connect(host, port);
                } else {
                    client.connect(host);
                }
                return;
            } catch (IOException ioEx) {
                logger.error("Connection failed.");
            }
        }
        throw new FtpException("Ivalid address.");
    }

    public Boolean uploadFile(String remoteName, String filePath) {
        if ((remoteName != null) && (filePath != null)) {
            try {
                return client.storeFile(remoteName, new FileInputStream(filePath));
            } catch (SocketException sEx) {
                logger.error(sEx);
            } catch (IOException ioEx) {
                logger.error(ioEx);
            }
        }
        return false;
    }

    public File downloadFile(String remotePath, String localPath) {
        if ((isLoggined) && (remotePath != null)) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader
                        (client.retrieveFileStream(remotePath)));
                StringBuilder strBuilder = new StringBuilder();
                while (reader.ready()) {
                    strBuilder.append(reader.readLine());
                }
                File readedFile = new File(localPath);
                OutputStream s = new FileOutputStream(readedFile);
                s.write(strBuilder.toString().getBytes());
                s.flush();
                return readedFile;                
            } catch (IOException ioEx) {
                
            }            
        }
        return null;
    }

    public Boolean login(String login, String password) {
        if (login != null) {
            try {
                return isLoggined = client.login(login, password);
            } catch (IOException ioEx) {
                logger.error("Invalid login or password", ioEx);
            }
        }
        return false;
    }

    public void close() {
        try {
            client.logout();
            client.disconnect();
        } catch (FTPConnectionClosedException fcEx) {
        } catch (IOException ioEx) {
        }
    }

    public Boolean isLoggined() {
        return isLoggined;
    }
}
