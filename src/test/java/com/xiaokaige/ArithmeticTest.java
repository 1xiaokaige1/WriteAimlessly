package com.xiaokaige;

import com.xiaokaige.entity.StudentDO;
import com.xiaokaige.generatorid.GeneratorUUID;
import com.xiaokaige.utils.SnowUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @author zengkai
 * @date 2021/6/24 15:59
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArithmeticTest {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5};
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        List<int[]> listM = new ArrayList<>();
        int[] arrTemp = new int[list.size()];
        List<int[]> result = dataOperate(listM, arrTemp, list);
        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }

    }


    public static List<int[]> dataOperate(List<int[]> resultList, int[] targetNum, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            targetNum[list.size() - 1 - i] = list.get(i);
            list.remove(i);
            dataOperate(resultList, targetNum, list);
            resultList.add(targetNum);
            for (int temp : targetNum) {
                list.add(temp);
            }
        }
        return resultList;
    }

    @Test
    public void test() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp.toString());

        Vector<StudentDO> studentDOVector = new Vector<>();
        studentDOVector.contains("abc");
        studentDOVector.add(StudentDO.builder().build());

        for (int i = 0; i < 10; i++) {
            int number = new Random().nextInt(9000) + 1000;
            System.out.println(number);
        }
    }


    @Test
    public void test111(){
        for (int i = 0; i < 200; i++) {
            System.out.println(GeneratorUUID.getUUID());
        }
    }

    @Test
    public void test12(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        long dateTimeLong = date.getTime();
        long localDateTimeLong = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println(dateTimeLong);
        System.out.println(localDateTimeLong);
    }



}
