/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.ftp.client;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.log4j.Logger;
import org.apache.mina.core.RuntimeIoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author slavabrodnitski
 */
public class FtpClientService {

    private static final Integer DEFAULT_FTP_LISTENER_PORT = 2121;
    private static final Logger logger = Logger.getLogger(FtpClientService.class);
    private final FTPClient client = new FTPClient();
    private final InetAddress host;
    private final Integer port;
    private Boolean logged = false;

    //TODO: must use try catch to this ex.
    //ex = org.apache.ftpserver.ftplet.FtpException
    //e.g. logger.error("Server is not started", ex);
    public FtpClientService() throws UnknownHostException, FtpException {

        this(InetAddress.getByName("localhost"), DEFAULT_FTP_LISTENER_PORT);
    }

    /**
     * Ftp client service initialization
     * @param host Host value to use
     * @param port Port number to use
     */
    public FtpClientService(InetAddress host, Integer port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Connect and login to FTP Server
     * @param login User login
     * @param password User password
     * @return <code>true</code> - in case of success login, <code>false</code> - otherwise
     * @throws FtpException In case of connection error
     */
    public Boolean login(String login, String password) throws FtpException {
        if (host != null) {
            try {
                if (port != null) {
                    client.connect(host, port);
                } else {
                    client.connect(host);
                }
                int reply = client.getReplyCode();

                if(!FTPReply.isPositiveCompletion(reply)) {
                    client.disconnect();
                    throw new FtpException("FTP server refused connection.");
                }

                return logged = client.login(login, password);
            } catch (IOException exception) {
                logger.error("Connection failed.", exception);
            }
        }
        return false;
    }

    /**
     * Logout and disconnect from FTP Server
     * @return <code>true</code> - in case of success logout and disconnect, <code>false</code> - otherwise
     */
    public Boolean logout() {
        try {
            return client.logout();
        } catch (IOException e) {
            logger.error("Error occurred on logout from FTP Server", e);
            return false;
        } finally {
            if (client.isConnected()) {
                try {
                    client.disconnect();
                } catch (IOException exception) {
                    logger.error("Connection failed.", exception);
                }
            }
        }
    }

    public Boolean uploadFile(String remoteName, File file) throws IOException {
        if ((remoteName != null) && (file != null)) {
            return client.storeFile(remoteName, new FileInputStream(file));
        }
        return false;
    }

    public InputStream downloadFile(final String filePath) throws IOException {
         if ((logged) && (filePath != null)) {
            final PipedOutputStream pout = new PipedOutputStream();
            PipedInputStream pin = new PipedInputStream(pout);
            Runnable exporter = new Runnable() {
                public void run() {
                    try {
                        client.retrieveFile(filePath, pout);
                    } catch (IOException ioEx) {
                        throw new RuntimeException(ioEx);
                    }
                }
            };
            Thread t = new Thread(exporter);
            t.start(); 
            return pin;
        }
        throw new FileNotFoundException(filePath + " file not found.");
    }

    public FTPFile[] getFileList(String pathname) throws IOException {
        FTPFile[] fileList = client.listFiles(pathname);
        return fileList;
    }

    public FTPFile[] getDirectoryList(String pathname) {
        FTPFile[] files = null;
        try {
            if (pathname != null) {
                files = client.mlistDir(pathname);
            } else {
                files = client.mlistDir();
            }
            return files;
        } catch (IOException ioEx) {
            throw new RuntimeIoException(ioEx);
        }
    }

    public void deleteFiles(String[] filePaths, String directoryPath) throws IOException {
        if (filePaths != null) {
            StringBuilder absPath = new StringBuilder();
            for (String path : filePaths) {
                absPath.append(directoryPath);
                absPath.append("/");
                absPath.append(path);
                client.deleteFile(absPath.toString());
                absPath.delete(0, absPath.length());
            }
        }
    }
}
