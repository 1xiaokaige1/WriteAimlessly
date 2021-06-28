package com.xiaokaige.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * @author zengkai
 * @date 2021/6/28 19:06
 */
@Component
public class TestAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Controller.class);

        beans.forEach((k,v)->{
            System.out.println("k: " + k);
            System.out.println("v: " + v);
        });
    }
}
