package com.xiaokaige;

import com.xiaokaige.strategy.aware.DefineAware;
import com.xiaokaige.strategy.dao.TargetInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zengkai
 * @date 2021/8/4 17:37
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StrategyTest {

    @Autowired
    private DefineAware defineAware;

    @Test
    public void test() {
        TargetInterface targetStrategy = defineAware.getTargetStrategy(1);
        int countOne = targetStrategy.methodOne();
        int countTwo = targetStrategy.methodTwo();
        System.out.println("countOne: " + countOne);
        System.out.println("countTwo: " + countTwo);
    }

}
