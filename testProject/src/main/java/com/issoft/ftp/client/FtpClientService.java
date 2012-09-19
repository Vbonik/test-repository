/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.ftp.client;

import java.io.ByteArrayOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.io.SocketInputStream;
import org.apache.mina.core.RuntimeIoException;

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

    //TODO: catch exceptions if server is not running/start
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
        throw new FtpException("Connection failed.");
    }

    public Boolean login(String login, String password) {
        if (login != null) {
            try {
                return logged = client.login(login, password);
            } catch (IOException ioEx) {
                logger.error("Invalid login or password", ioEx);
            }
        }
        return false;
    }

    //TODO: resolve file size problem
    // something bad with this stuff  or something other.
    // Because i can't upload files, more than 2048
    public Boolean uploadFile(String remoteName, File file) {
        if ((remoteName != null) && (file != null)) {
            try {
                return client.storeFile(remoteName, new FileInputStream(file));
            } catch (SocketException sEx) {
                logger.error(sEx);
            } catch (IOException ioEx) {
                logger.error(ioEx);
            }
        }
        return false;
    }

    public InputStream downloadFile(String filePath) throws FileNotFoundException {

        if ((logged) && (filePath != null)) {
            try {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                FileOutputStream outFileStream = null;
                File tempFile = File.createTempFile(Integer.valueOf(new Random().nextInt()).toString(), ".temp");
                try {
                    client.retrieveFile(filePath, byteStream);
                    byteStream.flush();
                    byte[] fileBytes = byteStream.toByteArray();
                    if(fileBytes.length == 0) {
                        return null;
                    }
                        
                    try {
                        outFileStream = new FileOutputStream(tempFile);
                        outFileStream.write(fileBytes);
                        outFileStream.flush();
                    } finally {
                        if (outFileStream != null) {
                            outFileStream.close();
                        }
                    }
                } finally {
                    if (byteStream != null) {
                        byteStream.close();
                    }
                }
                FileInputStream inStr = new FileInputStream(tempFile);
                return inStr;

            } catch (IOException e) {
                logger.error("File not found.", e);
            }
        }
        throw new FileNotFoundException(filePath + " file not found.");
    }

    public FTPFile[] getFileList(String pathname) {
        FTPFile[] fileList = null;
        try {
            fileList = client.listFiles(pathname);
        } catch (IOException e) {
            logger.error("No files on directory", e);
        }
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
        } catch(IOException ioEx) {
            throw new RuntimeIoException(ioEx);
        }
    }

    public void close() {
        try {
            client.logout();
            client.disconnect();
        } catch (FTPConnectionClosedException e) {
            logger.error("Logout client from FTP", e);
        } catch (IOException e) {
            logger.error("Disconnect client from FTP", e);
        }
    }

    public Boolean isLogged() {
        return logged;
    }
}
