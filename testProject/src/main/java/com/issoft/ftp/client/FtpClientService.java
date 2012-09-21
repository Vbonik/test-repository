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
import java.util.List;
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
    public Boolean uploadFile(String remoteName, File file) throws IOException {
        if ((remoteName != null) && (file != null)) {
            return client.storeFile(remoteName, new FileInputStream(file));
        }
        return false;
    }

    public InputStream downloadFile(String filePath) throws FileNotFoundException, IOException {

        if ((logged) && (filePath != null)) {

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            FileOutputStream outFileStream = null;
            File tempFile = File.createTempFile(Integer.valueOf(new Random().nextInt()).toString(), ".temp");
            try {
                client.retrieveFile(filePath, byteStream);
                byteStream.flush();
                byte[] fileBytes = byteStream.toByteArray();
                if (fileBytes.length == 0) {
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


        }
        throw new FileNotFoundException(filePath + " file not found.");
    }

    public FTPFile[] getFileList(String pathname) throws IOException {
        FTPFile[] fileList = null;
        fileList = client.listFiles(pathname);

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

    public void close() throws IOException {
        client.logout();
        client.disconnect();
    }

    public Boolean isLogged() {
        return logged;
    }
}
