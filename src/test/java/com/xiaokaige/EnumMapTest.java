package com.xiaokaige;

import com.xiaokaige.aware.TestAware;
import com.xiaokaige.enums.TestCodeEnum;
import com.xiaokaige.interfacedemo.InterfaceDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zengkai
 * @date 2021/6/29 8:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EnumMapTest {

    @Autowired
    private TestAware testAware;

    @Test
    public void test01(){
        InterfaceDemo interfaceDemo = testAware.getInterfaceDemo("abc001");
        interfaceDemo.add();
    }
}
