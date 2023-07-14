package com.xiaokaige.aware;

import com.xiaokaige.annotataion.StrategyAno;
import com.xiaokaige.enums.TestCodeEnum;
import com.xiaokaige.interfacedemo.InterfaceDemo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @author zengkai
 * @date 2021/6/28 19:06
 */
@Component
public class TestAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    Map<TestCodeEnum, Class<InterfaceDemo>> map = new EnumMap<>(TestCodeEnum.class);

    public InterfaceDemo getInterfaceDemo(String code) {
        return getInterfaceDemo(code, map);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(StrategyAno.class);

        beans.forEach((k, v) -> {
            Class<InterfaceDemo> InterfaceDemoClass = (Class<InterfaceDemo>) v.getClass();
            TestCodeEnum testCodeEnum = InterfaceDemoClass.getAnnotation(StrategyAno.class).value();
            List<Class<?>> list = Arrays.asList(InterfaceDemoClass.getInterfaces());
            if (list.contains(InterfaceDemo.class)) {
                System.out.println("true");
            }
            map.put(testCodeEnum, InterfaceDemoClass);
        });
    }


    public <T> T getInterfaceDemo(String code, Map<TestCodeEnum, Class<T>> map) {
        Class<T> interfaceDemoClass = map.get(TestCodeEnum.valueOf(code));
        return applicationContext.getBean(interfaceDemoClass);
    }
}
