package com.issoft.ftp.util;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nikitadavydov
 * Date: 9/6/12
 * Time: 9:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class RealPathSeparator {

    String fileID;
    String fileIDToUpload;
    String filePathToDownload;

    public RealPathSeparator(String realPath) {
        fileID = getFileIdentity(realPath);
        filePathToDownload = getRealFilePathToUpload(realPath);
        fileIDToUpload = getFileIdForUpload();
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getFilePathToDownload() {
        return filePathToDownload;
    }

    public void setFilePathToDownload(String filePathToDownload) {
        this.filePathToDownload = filePathToDownload;
    }

    public String getFileIDToUpload() {
        return fileIDToUpload;
    }

    public void setFileIDToUpload(String fileIDToUpload) {
        this.fileIDToUpload = fileIDToUpload;
    }

    private String getFileIdentity(String filePath) {
        Date date = new Date();
        StringBuffer buffer = new StringBuffer();
        String[] strings = splitFilePath(filePath);
        buffer.append(strings[0]).append(date.getTime()).append(".").append(strings[1]);
        return buffer.toString();
    }

    private String getRealFilePathToUpload(String filePath) {
        StringBuffer buffer = new StringBuffer();
        String[] strings = splitFilePath(filePath);
        buffer.append("D:/").append(strings[0]).append(".").append(strings[1]);
        return buffer.toString();
    }

    private String getFileIdForUpload() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("D:/").append(fileID);
        return buffer.toString();
    }

    private String[] splitFilePath(String filePath) {
        return filePath.split("\\.");
    }
}
