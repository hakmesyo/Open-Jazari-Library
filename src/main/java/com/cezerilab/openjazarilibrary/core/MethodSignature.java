/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cezerilab.openjazarilibrary.core;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ClassUtils;

/**
 *
 * @author BAP1
 */
public class MethodSignature {

    private String methodName;
    private List paramType = new ArrayList();
    private List paramVal = new ArrayList();

    public MethodSignature(String methodName, Object... arg) {
        this.methodName = methodName;
        for (int i = 0; i < arg.length; i ++) {
            this.paramType.add(arg[i].getClass());
            this.paramVal.add(arg[i]);
        }
    }

    public MethodSignature(String methodName) {
        this.methodName = methodName;
    }

    private static <T> List<T> pushBack(List<T> list, Class<T> typeKey) throws Exception {
        list.add(typeKey.getConstructor().newInstance());
        return list;
    }

    public Class[] getClasses() {
        int n = paramType.size();
        Class[] ret = new Class[n];
        for (int i = 0; i < n; i++) {
            try {
                String s=paramType.get(i).toString().replace("class ", "");
                ret[i] = ClassUtils.getClass(s);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MethodSignature.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }

    public Object[] getValues() {
        int n = paramType.size();
        Object[] ret = new Object[n];
        for (int i = 0; i < n; i++) {
            ret[i] = paramVal.get(i);
        }
        return ret;
    }

    public String getMethodName() {
        return methodName;
    }
}
