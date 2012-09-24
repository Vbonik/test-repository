package com.issoft.ftp.model;

import java.io.File;

/**
 * User: Никита
 * Date: 09.09.12
 */
public class FTPFile {
    private File userFile;
    private String userFileContentType;
    private String userFileFileName;
    private String[] fileNamesOnFTP;
    private String destination;


    public FTPFile() {
    }

    public FTPFile(String[] fileNamesOnFTP) {
        this.fileNamesOnFTP = fileNamesOnFTP;
    }

    public File getUserFile() {
        return userFile;
    }

    public void setUserFile(File userFile) {
        this.userFile = userFile;
    }

    public String getUserFileContentType() {
        return userFileContentType;
    }

    public void setUserFileContentType(String userFileContentType) {
        this.userFileContentType = userFileContentType;
    }

    public String getUserFileFileName() {
        return userFileFileName;
    }

    public void setUserFileFileName(String userFileFileName) {
        this.userFileFileName = userFileFileName;
    }

    public String[] getFileNamesOnFTP() {
        return fileNamesOnFTP;
    }

    public void setFileNamesOnFTP(String[] fileNamesOnFTP) {
        this.fileNamesOnFTP = fileNamesOnFTP;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
