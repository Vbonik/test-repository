package com.issoft.log;

import com.issoft.ftp.util.Constants;
import com.issoft.entity.dao.LogEntryDAO;
import com.issoft.log.mailer.model.Mail;
import com.issoft.log.mailer.model.MailStorage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.InputStream;

/**
 * Aspect that intercepts some ftp actions and:
 * <li>logs them to database</li>
 * <li>sends e-mail notifications to subscribed users</li>
 *
 * @author: AS
 */
@Aspect
public class LogMailAspect {

    private LogEntryDAO logEntryDAO;
    private static final String SPRING_SECURITY_AUTHENTICATION = "Spring security authentication";
    private static final String UPLOAD = "Upload";
    private static final String DOWNLOAD = "Download";
    private static final String DELETE = "Delete";


    /**
     * Failed spring security authentication.
     *
     * @param joinPoint
     */
    @After("execution(* org.springframework.security.web.authentication." +
            "SimpleUrlAuthenticationFailureHandler.onAuthenticationFailure(..))")
    public void springSecurityAuthenticationFailed(JoinPoint joinPoint) {
        Object[] paramValues = joinPoint.getArgs();
        AuthenticationException exception = (AuthenticationException) paramValues[2];
        Authentication authentication = exception.getAuthentication();
        String principalName = (String) authentication.getPrincipal();
        String action = SPRING_SECURITY_AUTHENTICATION;
        String status = Constants.FAILURE + ": " + exception.getMessage();
        logEntryDAO.saveEntry(principalName, "", action, status);
    }

    /**
     * Successful spring security authentication.
     *
     * @param joinPoint
     */
    @After("execution(* org.springframework.security.web.authentication." +
            "AuthenticationSuccessHandler.onAuthenticationSuccess(..))")
    public void springSecurityAuthenticationSuccess(JoinPoint joinPoint) {
        Object[] paramValues = joinPoint.getArgs();
        Authentication authentication = (Authentication) paramValues[2];
        User user = (User) authentication.getPrincipal();
        String action = SPRING_SECURITY_AUTHENTICATION;
        String status = Constants.SUCCESS;
        logEntryDAO.saveEntry(user.getUsername(), getAuthorities(user), action, status);
    }

    /**
     * Ftp. Login.
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(
            pointcut = "execution(* com.issoft.ftp.presentation.action.FtpAction.login()))",
            returning = "result")
    public void loginFtp(JoinPoint joinPoint, Object result) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String action = joinPoint.getSignature().getName();
        String status = result.toString();
        logEntryDAO.saveEntry(user.getUsername(), getAuthorities(user), action, status);
    }

    /**
     * Ftp. Download file pointcut.
     */
    @Pointcut("execution(* com.issoft.ftp.client.FtpClientService.downloadFile(..)))")
    public void download() {
    }

    /**
     * Ftp. Upload file pointcut.
     */
    @Pointcut("execution(* com.issoft.ftp.client.FtpClientService.uploadFile(..)))")
    public void upload() {
    }

    /**
     * Ftp. Delete file pointcut.
     */
    @Pointcut("execution(* com.issoft.ftp.client.FtpClientService.deleteFiles(..)))")
    public void delete() {
    }

    /**
     * Ftp. Download file.
     *
     * @param joinPoint
     * @param inputStream
     */
    @AfterReturning(pointcut = "download()",
            returning = "inputStream")
    public void downloadStart(JoinPoint joinPoint, InputStream inputStream) {
        String filePath = (String) joinPoint.getArgs()[0];
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String action = "Download file: " + filePath;
        String status = (inputStream != null ? Constants.SUCCESS : Constants.FAILURE);
        logEntryDAO.saveEntry(user.getUsername(), getAuthorities(user), action, status);

        //Adds mail to storage for sent later
        MailStorage.getInstance().addMail(new Mail(DOWNLOAD, filePath, user.getUsername()));
    }

    /**
     * Ftp. Exception while starting download/upload file.
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(pointcut = "download() || upload() || delete()",
            throwing = "exception")
    public void downloadStartException(JoinPoint joinPoint, Throwable exception) {
        String filePath = (String) joinPoint.getArgs()[0];
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String action = "Operation: " + joinPoint.getSignature().getName();
        String status = Constants.FAILURE + ": " + exception.getMessage();
        logEntryDAO.saveEntry(user.getUsername(), getAuthorities(user), action, status);
    }

    /**
     * Ftp. Upload file.
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "upload()",
            returning = "result")
    public void uploadStart(JoinPoint joinPoint, Boolean result) {
        String filePath = (String) joinPoint.getArgs()[0];
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String action = "Upload file: " + filePath;
        String status = (result ? Constants.SUCCESS : Constants.FAILURE);
        logEntryDAO.saveEntry(user.getUsername(), getAuthorities(user), action, status);

        //Adds mail to storage for sent later
        MailStorage.getInstance().addMail(new Mail(UPLOAD, filePath, user.getUsername()));
    }

    /**
     * Ftp. Delete files.
     *
     * @param joinPoint
     */
    @After(value = "delete()")
    public void deleteFiles(JoinPoint joinPoint) {
        String[] filePaths = (String[]) joinPoint.getArgs()[0];
        String directory = (String) joinPoint.getArgs()[1];
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StringBuffer action = new StringBuffer("Delete files from directory " + directory + ": ");
        for (String path : filePaths) {
            action.append(path + ", ");
        }
        logEntryDAO.saveEntry(user.getUsername(), getAuthorities(user), action.toString(), Constants.SUCCESS);

        //Adds mail to storage for sent later
        MailStorage.getInstance().addMail(new Mail(DELETE, action.toString(), user.getUsername()));
    }

    /**
     * Gets collection of user authorities and converting them to one string.
     *
     * @param user
     * @return String authorities
     */
    private String getAuthorities(User user) {
        StringBuffer authorities = new StringBuffer();
        for (GrantedAuthority authority : user.getAuthorities()) {
            authorities.append(authority.getAuthority() + ", ");
        }
        return authorities.toString();
    }


    public void setLogEntryDAO(LogEntryDAO logEntryDAO) {
        this.logEntryDAO = logEntryDAO;
    }
}
