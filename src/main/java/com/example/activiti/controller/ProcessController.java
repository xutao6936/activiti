package com.example.activiti.controller;

import com.example.activiti.workflow.CommonUtil;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/process")
public class ProcessController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @GetMapping(value = "/start/{input}")
    public String start(@PathVariable("input") String input){
        System.out.println("thread id is "+Thread.currentThread().getId());
        Map<String, Object> variables = new HashMap<>();
        variables.put("input",input);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ebailingtong",variables);
        System.out.println("ProcessController 流程id："+processInstance.getId());
        return CommonUtil.variableMap.get().get("result_body").toString();
    }

    @GetMapping(value = "/engin")
    public String engin(){
        List<Task> list = taskService.createTaskQuery().list();
        list.forEach(task ->{
            System.out.println("task name is "+task.getName());
        });
        return "ok";
    }
}
