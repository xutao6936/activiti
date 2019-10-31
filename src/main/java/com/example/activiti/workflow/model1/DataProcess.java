package com.example.activiti.workflow.model1;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * 数据加工
 */
public class DataProcess implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("thread id is "+Thread.currentThread().getId());
        System.out.println("DataProcess 流程 id："+delegateExecution.getId());
        System.out.println("start data_process");
        if(delegateExecution.hasVariable("model1_dataApply_result_code")){
            delegateExecution.removeVariable("model1_dataApply_result_code");
        }
        delegateExecution.setVariable("model1_dataProcess_result_body",delegateExecution.getVariable("input"));
        delegateExecution.setVariable("model1_dataProcess_result_code","fail");
    }
}
