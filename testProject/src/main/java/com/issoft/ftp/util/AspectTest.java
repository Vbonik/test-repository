package com.issoft.ftp.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author: AS
 */
@Aspect
public class AspectTest {

    @After("execution(* com.issoft.ftp.presentation.action.FtpAction.login())")
    public void logLogin(JoinPoint joinPoint) {

        System.out.println("logLogin() is running!");
        System.out.println("Intercept: " + joinPoint.getSignature());
        System.out.println("******");
    }
}
