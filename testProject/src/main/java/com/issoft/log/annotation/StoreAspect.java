/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.log.annotation;

import com.issoft.xml.ReflXmlBuilder;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *
 * @author slavabrodnitski
 */
public class StoreAspect implements MethodInterceptor {
    
    /**
     * Checks params of method for Storable annotating and adds it to file.   
     * @param methodInvocation 
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) {

        try {
            Object result = methodInvocation.proceed();
            ReflXmlBuilder xmlBuilder = new ReflXmlBuilder();
            Object[] args = methodInvocation.getArguments();
            for (Object arg : args) {
                Annotation annotation = arg.getClass().getAnnotation(Storable.class);
                if (annotation != null) {
                    xmlBuilder.addObjToFile(arg);
                    xmlBuilder.flush();
                    break;
                }
            }            
            return result;

        } catch (Throwable e) {            
        }
        return null;
    }
}
