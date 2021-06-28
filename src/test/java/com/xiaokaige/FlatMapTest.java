package com.xiaokaige;

import com.xiaokaige.reddison.RedissLockUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zengkai
 * @date 2021/6/23 15:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FlatMapTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        List<Integer> listOne = new ArrayList<>();
        List<Integer> listTwo = new ArrayList<>();

        listOne.add(1);
        listTwo.add(1);
        listOne.add(2);
        listTwo.add(2);

        List<int[]> result = listOne.stream().flatMap(one -> listTwo.stream().map(two -> new int[]{one, two})).collect(Collectors.toList());
    }

    @Test
    public void test02() {
        RLock lock = RedissLockUtil.lock("abc");
        try {
            boolean flagOne = lock.tryLock();
            System.out.println("flagOne: " + flagOne);
            new Thread(() -> {
                boolean flagTwo = lock.tryLock();
                System.out.println("flagTwo: " + flagTwo);
            }).start();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void test03(){
        redisTemplate.opsForValue().set("abc","001");
    }



}
