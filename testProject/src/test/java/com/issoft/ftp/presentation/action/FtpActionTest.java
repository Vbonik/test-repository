/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.ftp.presentation.action;

import com.issoft.ftp.client.FtpClientService;
import com.issoft.ftp.model.TempDirectory;
import java.io.InputStream;
import java.util.Map;
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
public class FtpActionTest {
    
    public FtpActionTest() {
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
     * Test of getFileInputStream method, of class FtpAction.
     */
    @Test
    public void testGetFileInputStream() {
        System.out.println("getFileInputStream");
        FtpAction instance = new FtpAction();
        InputStream expResult = null;
        InputStream result = instance.getFileInputStream();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFileInputStream method, of class FtpAction.
     */
    @Test
    public void testSetFileInputStream() {
        System.out.println("setFileInputStream");
        InputStream fileInputStream = null;
        FtpAction instance = new FtpAction();
        instance.setFileInputStream(fileInputStream);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFtpClientService method, of class FtpAction.
     */
    @Test
    public void testSetFtpClientService() {
        System.out.println("setFtpClientService");
        FtpClientService ftpClientService = null;
        FtpAction instance = new FtpAction();
        instance.setFtpClientService(ftpClientService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFtpClientService method, of class FtpAction.
     */
    @Test
    public void testGetFtpClientService() {
        System.out.println("getFtpClientService");
        FtpAction instance = new FtpAction();
        FtpClientService expResult = null;
        FtpClientService result = instance.getFtpClientService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentDirectory method, of class FtpAction.
     */
    @Test
    public void testGetCurrentDirectory() {
        System.out.println("getCurrentDirectory");
        FtpAction instance = new FtpAction();
        TempDirectory expResult = null;
        TempDirectory result = instance.getCurrentDirectory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentDirectory method, of class FtpAction.
     */
    @Test
    public void testSetCurrentDirectory() {
        System.out.println("setCurrentDirectory");
        TempDirectory currentDirectory = null;
        FtpAction instance = new FtpAction();
        instance.setCurrentDirectory(currentDirectory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of execute method, of class FtpAction.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        FtpAction instance = new FtpAction();
        String expResult = "";
        String result = instance.execute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of uploadFile method, of class FtpAction.
     */
    @Test
    public void testUploadFile() throws Exception {
        System.out.println("uploadFile");
        FtpAction instance = new FtpAction();
        String expResult = "";
        String result = instance.uploadFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadFile method, of class FtpAction.
     */
    @Test
    public void testDownloadFile() throws Exception {
        System.out.println("downloadFile");
        FtpAction instance = new FtpAction();
        String expResult = "";
        String result = instance.downloadFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileList method, of class FtpAction.
     */
    @Test
    public void testGetFileList() throws Exception {
        System.out.println("getFileList");
        FtpAction instance = new FtpAction();
        String expResult = "";
        String result = instance.getFileList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class FtpAction.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        FtpAction instance = new FtpAction();
        String expResult = "";
        String result = instance.login();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteFiles method, of class FtpAction.
     */
    @Test
    public void testDeleteFiles() throws Exception {
        System.out.println("deleteFiles");
        FtpAction instance = new FtpAction();
        String expResult = "";
        String result = instance.deleteFiles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setParameters method, of class FtpAction.
     */
    @Test
    public void testSetParameters() {
        System.out.println("setParameters");
        Map<String, String[]> parameters = null;
        FtpAction instance = new FtpAction();
        instance.setParameters(parameters);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParameters method, of class FtpAction.
     */
    @Test
    public void testGetParameters() {
        System.out.println("getParameters");
        FtpAction instance = new FtpAction();
        Map expResult = null;
        Map result = instance.getParameters();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
