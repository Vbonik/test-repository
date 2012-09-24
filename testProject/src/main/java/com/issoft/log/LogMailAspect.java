package com.issoft.log;

import com.issoft.log.database.LogEntryDAO;
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

    private String filePath;
    private LogEntryDAO logEntryDAO;
    private static final String SPRING_SECURITY_AUTHENTICATION = "Spring security authentication";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static final String UPLOAD = "Upload";
    private static final String DOWNLOAD = "Download";


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
        String status = FAILURE + ": " + exception.getMessage();
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
        String status = SUCCESS;
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
     * Ftp. Download file.
     */
    @Pointcut("execution(* com.issoft.ftp.client.FtpClientService.downloadFile(..)))")
    public void download() {
    }

    /**
     * Ftp. Upload file.
     */
    @Pointcut("execution(* com.issoft.ftp.client.FtpClientService.uploadFile(..)))")
    public void upload() {
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
        filePath = (String) joinPoint.getArgs()[0];
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String action = "Starting download file: " + filePath;
        String status = (inputStream != null ? SUCCESS : FAILURE);
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
    @AfterThrowing(pointcut = "download() || upload()",
            throwing = "exception")
    public void downloadStartException(JoinPoint joinPoint, Throwable exception) {
        filePath = (String) joinPoint.getArgs()[0];
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String action = "Operation: " + joinPoint.getSignature().getName() + "\nFile: " + filePath;
        String status = FAILURE + ": " + exception.getMessage();
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
        filePath = (String) joinPoint.getArgs()[0];
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String action = "Starting upload file: " + filePath;
        String status = (result ? SUCCESS : FAILURE);
        logEntryDAO.saveEntry(user.getUsername(), getAuthorities(user), action, status);

        //Adds mail to storage for sent later
        MailStorage.getInstance().addMail(new Mail(UPLOAD, filePath, user.getUsername()));
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
