package com.issoft.ftp.util;

import com.issoft.database.log.LogEntryDAO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author: AS
 */
@Aspect
public class LoggingAspect {

    private User principal;
    private String action;
    private String status;

    private LogEntryDAO logEntryDAO;

    @AfterReturning(
            pointcut = "execution(* com.issoft.ftp.presentation.action.FtpAction.login()))",
            returning = "result")
    public void logLogin(JoinPoint joinPoint, Object result) {
        principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        action = joinPoint.getSignature().getName();
        status = result.toString();
        logEntryDAO.saveEntry(principal, action, status);
    }

    public LogEntryDAO getLogEntryDAO() {
        return logEntryDAO;
    }

    public void setLogEntryDAO(LogEntryDAO logEntryDAO) {
        this.logEntryDAO = logEntryDAO;
    }
}
