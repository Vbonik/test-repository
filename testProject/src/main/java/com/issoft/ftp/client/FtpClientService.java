/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.ftp.client;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
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
        //TODO: must use try catch to this ex.
        this(InetAddress.getByName("localhost"), DEFAULT_FTP_LISTENER_PORT);
    }

    public Boolean login(String login, String password) {
        if (login != null) {
            try {
                return logged = client.login(login, password);
            }
            catch (IOException ioEx) {
                logger.error("Invalid login or password", ioEx);
            }
        }
        return false;
    }

    //TODO: catch exceptions if server is not running/start
    public FtpClientService(InetAddress host, Integer port) throws FtpException {
        if (host != null) {
            this.host = host;
            try {
                this.port = port;
                if (port != null) {
                    client.connect(host, port);
                }
                else {
                    client.connect(host);
                }
                return;
            }
            catch (IOException ioEx) {
                logger.error("Connection failed.");
            }
        }
        throw new FtpException("Connection failed.");
    }

    //TODO: resolve file size problem
    // something bad with this stuff  or something other.
    // Because i can't upload files, more than 2048
    public Boolean uploadFile(String remoteName, File file) {
        if ((remoteName != null) && (file != null)) {
            try {
                return client.storeFile(remoteName, new FileInputStream((File) file));
            }
            catch (SocketException sEx) {
                logger.error(sEx);
            }
            catch (IOException ioEx) {
                logger.error(ioEx);
            }
        }
        return false;
    }

    //TODO: rename
    public String[] getAllFileNamesOnFTPServer() {
        String[] filesOnFTP = null;
        try {
            filesOnFTP = client.listNames();
        }
        catch (IOException e) {
            logger.error("No files on directory", e);
        }
        return filesOnFTP;
    }

    public File downloadFile(String remotePath, String localPath) {
        File outputFile = null;
        InputStreamReader streamReader;
        OutputStream outputStream;
        if ((logged) && (remotePath != null)) {
            try {
                streamReader = new InputStreamReader(client.retrieveFileStream(remotePath));
                if (streamReader != null) {
                    outputFile = new File(localPath);
                    if (outputFile != null) {
                        try {
                            outputStream = new FileOutputStream(outputFile);
                            if (outputStream != null) {
                                outputStream.write(IOUtils.toByteArray(streamReader));
                                outputStream.close();
                            }
                        }
                        catch (IOException e) {
                            logger.error("Problems with outputStream", e);
                        }
                        catch (NullPointerException e) {
                            logger.error("Problems with outputStream", e);
                        }
                    }
                }
            }
            catch (IOException e) {
                logger.error("Problems with streamReader", e);
            }
        }
        return outputFile;
    }


    public void close() {
        try {
            client.logout();
            client.disconnect();
        }
        catch (FTPConnectionClosedException e) {
            logger.error("Logout client from FTP", e);
        }
        catch (IOException e) {
            logger.error("Disconnect client from FTP", e);
        }
    }

    public Boolean isLogged() {
        return logged;
    }
}
