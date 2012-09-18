package com.issoft.database.log;

import com.issoft.database.log.entry.LogEntryDAO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author: AS
 */
@Aspect
public class LoggingAspect {

    private User user;
    private String action;
    private String status;
    private LogEntryDAO logEntryDAO;


    @After("execution(* org.springframework.security.web.authentication." +
            "SimpleUrlAuthenticationFailureHandler.onAuthenticationFailure(..))")
    public void springSecurityAuthenticationFailed(JoinPoint joinPoint) {
        Object[] paramValues = joinPoint.getArgs();
        AuthenticationException exception = (AuthenticationException) paramValues[2];
        Authentication authentication = exception.getAuthentication();
        String principalName = (String) authentication.getPrincipal();
        action = "Spring security authentication";
        status = "FAILURE: " + exception.getMessage();
        logEntryDAO.saveEntry(principalName, "", action, status);
    }

    @After("execution(* org.springframework.security.web.authentication." +
            "AuthenticationSuccessHandler.onAuthenticationSuccess(..))")
    public void springSecurityAuthenticationSuccess(JoinPoint joinPoint) {
        Object[] paramValues = joinPoint.getArgs();
        Authentication authentication = (Authentication) paramValues[2];
        user = (User) authentication.getPrincipal();
        action = "Spring security authentication";
        status = "SUCCESS";
        logEntryDAO.saveEntry(user.getUsername(), getAuthorities(user), action, status);
    }

    @AfterReturning(
            pointcut = "execution(* com.issoft.ftp.presentation.action.FtpAction.login()))",
            returning = "result")
    public void logLogin(JoinPoint joinPoint, Object result) {
        user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        action = joinPoint.getSignature().getName();
        status = result.toString();
        logEntryDAO.saveEntry(user.getUsername(), getAuthorities(user), action, status);
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

    public LogEntryDAO getLogEntryDAO() {
        return logEntryDAO;
    }

    public void setLogEntryDAO(LogEntryDAO logEntryDAO) {
        this.logEntryDAO = logEntryDAO;
    }
}
