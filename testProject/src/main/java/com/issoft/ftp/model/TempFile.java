package com.issoft.ftp.model;

import java.io.File;
import org.apache.commons.net.ftp.FTPFile;

public class TempFile {

    private File file;
    private FTPFile ftpFileInf;
    private String name;
    private String fileFileName;
    public TempFile() {
    }

    public TempFile(File file, FTPFile ftpFileInf, String fileName) {
        this.file = file;
        this.ftpFileInf = ftpFileInf;
        this.name = fileName;
    }

    public TempFile(FTPFile ftpFileInf) {
        this.ftpFileInf = ftpFileInf;
    }
    public TempFile(String fileName) {
        this.name = fileName;
    }
    public TempFile(String fileName, FTPFile ftpFileInf) {
        this.name = fileName;
        this.ftpFileInf = ftpFileInf;
    }
    public TempFile(String fileName, File file) {
        this(fileName);
        this.file = file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setName(String fileName) {
        this.name = fileName;
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public FTPFile getFtpFileInf() {
        return ftpFileInf;
    }

    public void setFtpFileInf(FTPFile ftpFileInf) {
        this.ftpFileInf = ftpFileInf;
    }
   
    public String getFileFileName() {
        return fileFileName;
    }
   
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
    
}