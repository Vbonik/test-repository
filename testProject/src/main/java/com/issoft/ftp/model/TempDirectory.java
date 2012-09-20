/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.ftp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author slavabrodnitski
 */
public class TempDirectory  {

    private TempFile currentFile = new TempFile();
    private Integer typeOfFile;
    private List fileList = new ArrayList();
    private String absolutePath = new String();
    private String name;

    public TempDirectory() {
    }
    public TempDirectory(String name) {
        this.name = name;
    }

    public TempDirectory(String name, String absolutePath) {
        this(name);
        this.absolutePath = absolutePath;
    }
    
    public String getAbsolutePath() {
        return absolutePath;
    }

    public List getFileList() {
        return fileList;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public void setFileList(List fileList) {
        this.fileList = fileList;
    }

    public void setCurrentFile(TempFile currentFile) {
        this.currentFile = currentFile;
    }

    public TempFile getCurrentFile() {
        return currentFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeOfFile() {
        return typeOfFile;
    }

    public void setTypeOfFile(Integer typeOfFile) {
        this.typeOfFile = typeOfFile;
    }    
    
    public void addDirectoryToList(TempDirectory file) {
        fileList.add(file);
    } 
}
