package com.issoft.ftp.model;

import java.io.File;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Created with IntelliJ IDEA.
 * User: Никита
 * Date: 09.09.12
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
public class FTPFile {
    private File userFile;
    private String userFileContentType;
    private String userFileFileName;
    

    public FTPFile() {
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
}
