package com.issoft.ftp.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * User: Никита
 * Date: 09.09.12
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
public class FTPFile {
    private MultipartFile userFile;
    private String userFileContentType;
    private String userFileFileName;
    private String[] filenamesOnFTP;


    public FTPFile() {
    }

    public FTPFile(String[] filenamesOnFTP) {
        this.filenamesOnFTP = filenamesOnFTP;
    }

    public MultipartFile getUserFile() {
        return userFile;
    }

    public void setUserFile(MultipartFile userFile) {
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

    public String[] getFilenamesOnFTP() {
        return filenamesOnFTP;
    }

    public void setFilenamesOnFTP(String[] filenamesOnFTP) {
        this.filenamesOnFTP = filenamesOnFTP;
    }
}
