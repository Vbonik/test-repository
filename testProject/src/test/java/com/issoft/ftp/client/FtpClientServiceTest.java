/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.ftp.client;

import java.io.File;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author slavabrodnitski
 */
public class FtpClientServiceTest {
    
    public FtpClientServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class FtpClientService.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String login = "";
        String password = "";
        FtpClientService instance = new FtpClientService();
        Boolean expResult = null;
        Boolean result = instance.login(login, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of uploadFile method, of class FtpClientService.
     */
    @Test
    public void testUploadFile() throws Exception {
        System.out.println("uploadFile");
        String remoteName = "";
        File file = null;
        FtpClientService instance = new FtpClientService();
        Boolean expResult = null;
        Boolean result = instance.uploadFile(remoteName, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadFile method, of class FtpClientService.
     */
    @Test
    public void testDownloadFile() throws Exception {
        System.out.println("downloadFile");
        String filePath = "";
        FtpClientService instance = new FtpClientService();
        InputStream expResult = null;
        InputStream result = instance.downloadFile(filePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileList method, of class FtpClientService.
     */
    @Test
    public void testGetFileList() throws Exception {
        System.out.println("getFileList");
        String pathname = "";
        FtpClientService instance = new FtpClientService();
        FTPFile[] expResult = null;
        FTPFile[] result = instance.getFileList(pathname);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirectoryList method, of class FtpClientService.
     */
    @Test
    public void testGetDirectoryList() {
        System.out.println("getDirectoryList");
        String pathname = "";
        FtpClientService instance = new FtpClientService();
        FTPFile[] expResult = null;
        FTPFile[] result = instance.getDirectoryList(pathname);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteFiles method, of class FtpClientService.
     */
    @Test
    public void testDeleteFiles() throws Exception {
        System.out.println("deleteFiles");
        String[] filePaths = null;
        String directoryPath = "";
        FtpClientService instance = new FtpClientService();
        instance.deleteFiles(filePaths, directoryPath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class FtpClientService.
     */
    @Test
    public void testClose() throws Exception {
        System.out.println("close");
        FtpClientService instance = new FtpClientService();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLogged method, of class FtpClientService.
     */
    @Test
    public void testIsLogged() {
        System.out.println("isLogged");
        FtpClientService instance = new FtpClientService();
        Boolean expResult = null;
        Boolean result = instance.isLogged();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
