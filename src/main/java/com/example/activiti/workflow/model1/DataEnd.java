package com.example.activiti.workflow.model1;

import com.example.activiti.workflow.CommonUtil;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据处理结束
 */
public class DataEnd implements JavaDelegate {



    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("thread id is "+Thread.currentThread().getId());
        System.out.println("DataEnd 流程id ："+delegateExecution.getId());
        // 流程在数据准备接口失败
        if(delegateExecution.hasVariable("model1_dataApply_result_code")){
            Object test = delegateExecution.getVariable("model1_dataApply_result_body");
            ConcurrentHashMap<String,Object> maps = new ConcurrentHashMap<>();
            maps.put("result_body",test);
            CommonUtil.variableMap.set(maps);
            delegateExecution.setVariable("model1_result_code","fail");
        }else if (delegateExecution.hasVariable("model1_dataProcess_result_code")){
            String resultCode = delegateExecution.getVariable("model1_dataProcess_result_code").toString();
            if("fail".equals(resultCode)){
                Object test = delegateExecution.getVariable("model1_dataProcess_result_body");
                ConcurrentHashMap<String,Object> maps = new ConcurrentHashMap<>();
                maps.put("result_body",test);
                CommonUtil.variableMap.set(maps);
                delegateExecution.setVariable("model1_result_code","fail");
            }else if("success".equals(resultCode)){
                delegateExecution.setVariable("model1_result_code","success");
            }
        }
        System.out.println("process end");
    }
}
