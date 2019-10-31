package com.example.activiti.workflow;

import java.util.concurrent.ConcurrentHashMap;

public class CommonUtil {

    public static ThreadLocal<ConcurrentHashMap<String,Object>> variableMap = new ThreadLocal<>();

    public static String SUCCESS = "success";
    public static String FAIL = "fail";


}
