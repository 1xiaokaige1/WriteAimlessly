package com.xiaokaige.strategy.dao.impl;

import com.xiaokaige.strategy.anno.DefineAnnotation;
import com.xiaokaige.strategy.dao.TargetInterface;
import com.xiaokaige.strategy.enums.DefineEnum;
import org.springframework.stereotype.Component;

/**
 * @author zengkai
 * @date 2021/8/4 17:03
 */
@DefineAnnotation(DefineEnum.STRATEGY_TWO)
@Component
public class ImplementTwo implements TargetInterface {
    @Override
    public int methodOne() {
        return 2;
    }

    @Override
    public int methodTwo() {
        return 2;
    }

    @Override
    public int methodThree() {
        return 2;
    }
}
