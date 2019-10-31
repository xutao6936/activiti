package com.example.activiti.workflow.model1;

import com.example.activiti.config.SpringContextUtils;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 数据准备
 */
public class DataApply implements JavaDelegate {

    RestTemplate restTemplate = SpringContextUtils.getBean(RestTemplate.class);
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("thread id is "+Thread.currentThread().getId());
        System.out.println("DataApply id："+delegateExecution.getId());
        System.out.println("start execute data_apply");
        delegateExecution.setVariable("model1_dataApply_result_body","test");
        delegateExecution.setVariable("model1_dataApply_result_code","success");
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:9999/course/list", String.class);
        System.out.println("课程表是: "+forEntity.getBody());
    }
}
