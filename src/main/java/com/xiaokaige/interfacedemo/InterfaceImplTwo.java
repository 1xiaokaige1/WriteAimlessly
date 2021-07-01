package com.xiaokaige.interfacedemo;

import com.xiaokaige.annotataion.StrategyAno;
import com.xiaokaige.enums.TestCodeEnum;
import org.springframework.stereotype.Service;

/**
 * @author zengkai
 * @date 2021/6/29 9:01
 */
@StrategyAno(TestCodeEnum.TEST_FAILURE_CODE)
@Service
public class InterfaceImplTwo implements InterfaceDemo{
    @Override
    public void add() {
        System.out.println("添加方法二");
    }

    @Override
    public void delete() {
        System.out.println("删除方法二");
    }
}
