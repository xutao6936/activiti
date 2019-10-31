package com.example.activiti.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringContextUtils implements ApplicationContextAware {

    private static  ApplicationContext applicationContext = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextUtils.applicationContext==null){
            SpringContextUtils.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static <T> T getBean(String beanName){
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clz){
        return applicationContext.getBean(clz);
    }

    public static <T> T registryBean(ConfigurableApplicationContext configurableApplicationContext,String name,Class<T> clz,Object... args){
        if(configurableApplicationContext.containsBean(name)){
            Object bean = configurableApplicationContext.getBean(name);
            if(bean.getClass().isAssignableFrom(clz)){
                return (T) bean;
            }

        }
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clz);
        for(Object arg:args){
            beanDefinitionBuilder.addConstructorArgValue(arg);
        }
        AbstractBeanDefinition rawBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) configurableApplicationContext.getParentBeanFactory();
        beanDefinitionRegistry.registerBeanDefinition(name,rawBeanDefinition);
        return configurableApplicationContext.getBean(name,clz);
    }

    public static <T> T registryBean(String name,Class<T> clz,Object... args){
        return registryBean((ConfigurableApplicationContext) applicationContext,name,clz,args);
    }


}
