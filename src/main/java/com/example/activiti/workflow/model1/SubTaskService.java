package com.example.activiti.workflow.model1;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

@Slf4j
public class SubTaskService implements JavaDelegate {


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("当前子流程id：{} getCurrentActivityName is {} ",execution.getCurrentActivityId(),execution.getCurrentActivityName());
    }
}
