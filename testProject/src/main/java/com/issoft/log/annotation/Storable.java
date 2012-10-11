/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author slavabrodnitski
 */
@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Storable {
}
