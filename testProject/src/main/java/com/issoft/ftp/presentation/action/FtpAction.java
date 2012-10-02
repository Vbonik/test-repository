package com.issoft.ftp.presentation.action;

import com.issoft.ftp.client.FtpClientService;
import com.issoft.ftp.model.TempDirectory;
import com.issoft.ftp.model.TempFile;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.issoft.ftp.util.Constants.FAILURE;

/**
 * @author slavabrodnitski
 */
public class FtpAction extends ActionSupport implements ParameterAware {

    public static final Integer DIRECTORY_TYPE_ONLY = 1;
    public static final Integer FILE_TYPE_ONLY = 2;

    private InputStream fileInputStream;
    private TempDirectory currentDirectory = new TempDirectory();
    private FtpClientService ftpClientService;

    Map<String, String[]> parameters;

    //getters & setters
    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public void setFtpClientService(FtpClientService ftpClientService) {
        this.ftpClientService = ftpClientService;
    }

    public FtpClientService getFtpClientService() {
        return ftpClientService;
    }

    public TempDirectory getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(TempDirectory currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    //file
    public String uploadFile() throws FtpException, IOException {
        String tempPath = currentDirectory.getAbsolutePath().replace("_", "/");
        Boolean fileUpload = ftpClientService.uploadFile(tempPath + "/"
                + currentDirectory.getCurrentFile().getFileFileName(),
                currentDirectory.getCurrentFile().getFile());
        if (fileUpload) {
            currentDirectory.setAbsolutePath("");
            return SUCCESS;
        } else {
            return FAILURE;
        }

    }

    public String downloadFile() throws FtpException, IOException {
        try {
            String tempPath = currentDirectory.getAbsolutePath().replace("_", "/");
            fileInputStream = ftpClientService.downloadFile(tempPath + "/" + currentDirectory.getCurrentFile().getName());
            if (fileInputStream != null) {
                return SUCCESS;
            }
        } catch (NullPointerException e) {
        }
        return FAILURE;
    }

    public String getFileList() throws FtpException, IOException {
        List files = new ArrayList();
        currentDirectory.setFileList(files);
        Integer typeOfFile = Integer.valueOf(currentDirectory.getTypeOfFile());
        String tempPath = currentDirectory.getAbsolutePath();
        if (tempPath != null) {
            currentDirectory.setAbsolutePath(tempPath);
            tempPath = tempPath.replace("_", "/");
        }

        for (FTPFile file : ftpClientService.getFileList(tempPath)) {
            if (FILE_TYPE_ONLY.equals(typeOfFile)) {
                if (file.isFile()) {
                    TempFile temp = new TempFile(file.getName(), file);
                    files.add(temp);
                }
            } else if (DIRECTORY_TYPE_ONLY.equals(typeOfFile)) {
                if (file.isDirectory()) {
                    TempDirectory tempDir = buildDirTree(file.getName(), file.getName());
                    files.add(tempDir);
                }
            }
        }
        currentDirectory.setName("root");
        return SUCCESS;
    }

    //autorization
    public String login() throws FtpException, IOException {
        User principal =  (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isLoggined = ftpClientService.login(principal.getUsername(), principal.getPassword());
        if (isLoggined) {
            if (principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "adminLogin";
            } else if (principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                return "userLogin";
            }
            return FAILURE;
        } else {
            return FAILURE;
        }
    }

    public String logout() {
        boolean isLogoutSuccess = ftpClientService.logout();
        if (isLogoutSuccess) {
            return SUCCESS;
        } else {
            LOG.error("Error on logout");
            return FAILURE;
        }
    }

    public String deleteFiles() throws IOException, FtpException {
        if (parameters != null) {
            String tempPath = currentDirectory.getAbsolutePath().replace("_", "/");
            ftpClientService.deleteFiles(parameters.get("delete"), tempPath);
            currentDirectory.setTypeOfFile(DIRECTORY_TYPE_ONLY);
            currentDirectory.setAbsolutePath("");
            getFileList();
            return SUCCESS;
        }
        return FAILURE;
    }


    @Override
    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }

    private TempDirectory buildDirTree(String name, String path) {
        List dirs = new ArrayList();
        TempDirectory tempDir = new TempDirectory(name, path);
        String tempPath = path.replace("_", "/");
        for (FTPFile dir : ftpClientService.getDirectoryList(tempPath)) {
            if (dir.isDirectory()) {

                TempDirectory subDir = buildDirTree(dir.getName(), path + "_" + dir.getName());
                tempDir.addDirectoryToList(subDir);
            }
        }
        return tempDir;
    }


}
