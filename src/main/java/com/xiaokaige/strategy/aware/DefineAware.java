package com.xiaokaige.strategy.aware;

import com.xiaokaige.strategy.anno.DefineAnnotation;
import com.xiaokaige.strategy.dao.TargetInterface;
import com.xiaokaige.strategy.enums.DefineEnum;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author zengkai
 * @date 2021/8/4 17:05
 */
@Component
public class DefineAware implements ApplicationContextAware {

    Map<DefineEnum, Class<TargetInterface>> targetInterfaceStrategy = new EnumMap<>(DefineEnum.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(DefineAnnotation.class);

        beansWithAnnotation.forEach((k, v) -> {
            Class<TargetInterface> targetInterfaceClass = (Class<TargetInterface>) v.getClass();
            DefineAnnotation defineAnnotation = targetInterfaceClass.getAnnotation(DefineAnnotation.class);
            DefineEnum defineEnum = defineAnnotation.value();
            targetInterfaceStrategy.put(defineEnum, targetInterfaceClass);
        });
    }

    public <T> T getStrategy(Integer strategyCode, Map<DefineEnum, Class<T>> strategyMap) {
        Class<T> targetInterfaceClass = strategyMap.get(DefineEnum.getDefineEnum(strategyCode));
        return applicationContext.getBean(targetInterfaceClass);
    }

    public TargetInterface getTargetStrategy(Integer code) {
        return this.getStrategy(code, targetInterfaceStrategy);
    }


}
