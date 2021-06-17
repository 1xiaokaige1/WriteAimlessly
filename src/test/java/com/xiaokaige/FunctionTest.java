package com.xiaokaige;

import com.xiaokaige.compare.StudentComparator;
import com.xiaokaige.entity.StudentDO;
import com.xiaokaige.predicate.TestConstructPredicate;
import com.xiaokaige.predicate.TestFunction;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zengkai
 * @date 2021/6/15 14:41
 */
@SpringBootTest
public class FunctionTest {

    @Test
    public void test01() {
        int[] arr = {2, 5, 3, 1, 1};

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test02() {
        int[] arr = {2, 5, 3, 1, 1};

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test03() {
        int[] arr = {2, 5, 3, 1, 1};
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            int min = arr[index];
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            arr[index] = arr[i];
            arr[i] = min;
        }

        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void test04() throws IOException {
        String fileInfoOne = TestFunction.getFileInfo(BufferedReader::readLine);

        String fileInfoTwo = TestFunction.getFileInfo((BufferedReader br) -> br.readLine() + br.readLine());

        System.out.println(fileInfoOne);
        System.out.println("------");
        System.out.println(fileInfoTwo);
    }

    /**
     * 利用函数式接口来实现行为参数化
     */
    @Test
    public void test05() {
        List<StudentDO> list = new ArrayList<>();
        StudentDO studentDO = new StudentDO();
        studentDO.setId(1L);
        studentDO.setName("小曾");
        studentDO.setAge(25);
        studentDO.setAddress("深圳市龙岗区");

        StudentDO studentDO2 = new StudentDO();
        studentDO2.setId(1L);
        studentDO2.setName("小陈");
        studentDO2.setAge(16);
        studentDO2.setAddress("深圳市南山区");

        list.add(studentDO);
        list.add(studentDO2);

        List<StudentDO> resultList = TestFunction.filterData(list, studentDOTemp -> studentDOTemp.getAge() > 18);
        System.out.println(resultList);
        System.out.println("------");

        List<StudentDO> resultListTwo = TestFunction.filterData(list, studentDOSecond -> "深圳市南山区".equals(studentDOSecond.getAddress()));
        System.out.println(resultListTwo);
    }

    @Test
    public void test06() {
        TestConstructPredicate<Long, String, Integer, String, StudentDO> selfDefineTest = StudentDO::new;

        StudentDO studentDO = selfDefineTest.apply(1L, "小陈", 24, "深圳市龙岗区");

        System.out.println(studentDO);

    }

    @Test
    public void test07() {
        List<Integer> listOne = Arrays.asList(1, 2, 3);
        List<Integer> listTwo = Arrays.asList(2, 4);

        List<int[]> resultOne = listOne
                .stream()
                .flatMap(i -> listTwo.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        System.out.println("resultOne:" + resultOne);

        List<int[]> resultTwo = listOne
                .stream()
                .flatMap(i -> listTwo.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        System.out.println("resultTwo:" + resultTwo);
    }

    @Test
    public void test08(){
        List<StudentDO> list = new ArrayList<>();
        StudentDO studentDOOne = new StudentDO(1L, "小曾", 24, "深圳市龙岗区");
        StudentDO studentDOTwo = new StudentDO(2L, "小陈", 24, "深圳市南山区");
        list.add(studentDOOne);
        list.add(studentDOTwo);
        List<StudentDO[]> studentResult = list
                .stream()
                .flatMap(studentDO -> list.stream().map(studentDOTemp -> new StudentDO[]{studentDO, studentDOTemp}))
                .collect(Collectors.toList());
        System.out.println(studentResult);
    }

    @Test
    public void test09(){
        List<StudentDO> list = new ArrayList<>();
        StudentDO studentDOOne = new StudentDO(1L, "小曾", 24, "深圳市龙岗区");
        StudentDO studentDOTwo = new StudentDO(2L, "小陈", 22, "深圳市南山区");
        list.add(studentDOOne);
        list.add(studentDOTwo);

        list.sort(new StudentComparator());
        System.out.println(list);

        list.stream().findFirst().ifPresent(studentDO -> studentDO.setAddress("广州市天河区"));
        System.out.println(list.get(0));

        list.sort((Comparator.comparingInt(StudentDO::getAge)));
        System.out.println(list);

        Integer sum = list.stream().collect(Collectors.summingInt(StudentDO::getAge));
        System.out.println(sum);
    }

    @Test
    public void test10(){
        List<StudentDO> list = new ArrayList<>();
        StudentDO studentDOOne = new StudentDO(1L, "小曾", 24, "深圳市龙岗区");
        StudentDO studentDOTwo = new StudentDO(2L, "小陈", 22, "深圳市南山区");
        list.add(studentDOOne);
        list.add(studentDOTwo);

        String addressStr = list.stream().map(StudentDO::getAddress).collect(Collectors.joining("/"));
        System.out.println(addressStr);
    }

    @Test
    public void tes11(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(9);
        list.add(2);
        list.add(3);
        Integer maxNumber = list.stream().reduce((a, b) -> a > b ? a : b).get();
        System.out.println(maxNumber);
    }




}
