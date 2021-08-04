package com.xiaokaige.strategy.dao.impl;

import com.xiaokaige.strategy.anno.DefineAnnotation;
import com.xiaokaige.strategy.dao.TargetInterface;
import com.xiaokaige.strategy.enums.DefineEnum;
import org.springframework.stereotype.Component;

/**
 * @author zengkai
 * @date 2021/8/4 17:03
 */
@DefineAnnotation(DefineEnum.STRATEGY_ONE)
@Component
public class ImplementOne implements TargetInterface {
    @Override
    public int methodOne() {
        return 1;
    }

    @Override
    public int methodTwo() {
        return 1;
    }

    @Override
    public int methodThree() {
        return 1;
    }
}
