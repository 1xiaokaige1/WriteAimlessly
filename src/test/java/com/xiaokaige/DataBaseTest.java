package com.xiaokaige;

import com.xiaokaige.kafka.KfkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataBaseTest {

    //@Autowired
    //private RongLiveClient rongLiveClient;

    @Autowired
    private KfkService kfkService;

    /*@Test
    public void test01(){
        StartCloudPlayParam startCloudPlayParam = new StartCloudPlayParam();
        startCloudPlayParam.setRoomId("123123");
        startCloudPlayParam.setUserId("1231");
        startCloudPlayParam.setUsername("fafa");
        startCloudPlayParam.setMediaType(0);
        startCloudPlayParam.setRoomType(0);
        ResponseResult result = rongLiveClient.startCloudPlay(startCloudPlayParam);
        System.out.println("result = " + result);
    }*/

    /*@Test
    public void test02(){
        CloseCloudPlayParam closeCloudPlayParam = new CloseCloudPlayParam();
        closeCloudPlayParam.setRoomId("123123");
        closeCloudPlayParam.setUserId("1231");
        closeCloudPlayParam.setUsername("fafa");
        ResponseResult result = rongLiveClient.closeCloudPlay(closeCloudPlayParam);
        System.out.println("result = " + result);
    }*/

    @Test
    public void test03(){
        kfkService.sendMsg("topic1", "fsdf");
    }
}