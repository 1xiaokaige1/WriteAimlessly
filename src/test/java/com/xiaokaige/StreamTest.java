package com.xiaokaige;

import cn.hutool.crypto.digest.MD5;
import com.xiaokaige.config.StlConfig;
import com.xiaokaige.entity.Dish;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zengkai
 * @date 2021/6/18 14:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StreamTest {


    private List<Dish> list;

    @Autowired
    private StlConfig stlConfig;

    @Before
    public void before() {
        list = new ArrayList<>();
        Dish meat = new Dish(false, "meat", 1000, "type_one");
        Dish tomato = new Dish(true, "tomato", 200, "type_two");
        Dish milk = new Dish(false, "milk", 800, "type_third");
        list.add(meat);
        list.add(tomato);
        list.add(milk);
    }

    @Test
    public void test01() {
        //计算总数流
        long count = list.stream().count();
        System.out.println(count);

        //分组流
        Map<Boolean, List<Dish>> resultMap = list
                .stream()
                .collect(Collectors.groupingBy(dish -> dish.getCalories() > 500));
        System.out.println(resultMap);
    }

    @Test
    public void test02() {
        //获取最大数据流
        Dish maxCaloriesDish = list.stream().max(Comparator.comparingInt(Dish::getCalories)).get();
        System.out.println(maxCaloriesDish);

        //获取最小数据流
        Dish minCaloriesDish = list.stream().min(Comparator.comparingInt(Dish::getCalories)).get();
        System.out.println(minCaloriesDish);
    }

    @Test
    public void test03() {
        //获取流中的数据的统计信息
        IntSummaryStatistics summaryData = list.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        long sumCalories = summaryData.getSum();
        double averageCalories = summaryData.getAverage();
        long count = summaryData.getCount();
        int maxCalories = summaryData.getMax();
        int minCalories = summaryData.getMin();
        System.out.println(sumCalories);
        System.out.println("------");
        System.out.println(averageCalories);
        System.out.println("------");
        System.out.println(count);
        System.out.println("------");
        System.out.println(maxCalories);
        System.out.println("------");
        System.out.println(minCalories);
    }

    @Test
    public void test04() {
        //使用流进行数据拼接
        /*String allDishNameStr = list.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(allDishNameStr);*/

        /*boolean flag = list.stream().noneMatch(dish -> dish.getCalories() > 1000);
        System.out.println("flag: " + flag);*/

        /*Optional<Dish> optionResult = list.stream().filter(dish -> "meat".equals(dish.getName())).findAny();
        optionResult.ifPresent(System.out::println);*/

        /*Integer countResult = list.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        System.out.println("countResult: " + countResult);*/

        /*Integer sumCalories = list.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println("sumCalories: " + sumCalories );*/

        /*int result = list.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("result: " + result);*/

        OptionalInt maxData = list.stream().mapToInt(Dish::getCalories).max();
        int realMaxData = maxData.orElse(1500);
        System.out.println("realMaxData: " + realMaxData);

        /*Dish dish = list.stream().reduce((a, b) -> a.getCalories() > b.getCalories() ? a : b).get();
        System.out.println(dish);*/

        List<Dish[]> list = this.list.stream().flatMap(i -> this.list.stream().map(j -> new Dish[]{i, j})).collect(Collectors.toList());
        System.out.println(list);

    }

    @Test
    public void test05() {
        //按食物calories进行分类
        Map<String, List<Dish>> foodCategoriesMap = list.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() < 500) {
                return "diet";
            } else if (dish.getCalories() < 900) {
                return "normal";
            } else {
                return "fat";
            }
        }));
        System.out.println(foodCategoriesMap);
    }

    @Test
    public void test06() {
        //按食物calories进行分类,使用groupingBy收集器
        Map<String, Map<String, List<Dish>>> foodCategoriesMap = list.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() < 500) {
                return "diet";
            } else if (dish.getCalories() < 900) {
                return "normal";
            } else {
                return "fat";
            }
        })));
        System.out.println(foodCategoriesMap);
    }

    @Test
    public void test07() {
        Map<String, Dish> dishMap = list
                .stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(dishMap);

        Map<String, Integer> caloriesMap = list
                .stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
        System.out.println(caloriesMap);

        Map<String, HashSet<String>> resultMap = list.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
            if (dish.getCalories() < 400) {
                return "diet";
            } else if (dish.getCalories() < 800) {
                return "normal";
            } else {
                return "fat";
            }
        }, Collectors.toCollection(HashSet<String>::new))));
        System.out.println(resultMap);
    }

    @Test
    public void test08() {
       /*Map<Boolean, List<Dish>> resultMap = list.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(resultMap);*/

        Map<String, Dish> resultMapTwo = list.stream().collect(Collectors.toMap(Dish::getType, Function.identity()));
    }

    @Test
    public void test09() {
        Function<Integer, Integer> f1 = x1 -> x1 + 1;
        Function<Integer, Integer> f2 = x2 -> x2 * 2;
        Integer resultOne = f1.andThen(f2).apply(1);
        System.out.println("resultOne: " + resultOne);

        Integer resultTwo = f1.compose(f2).apply(1);
        System.out.println("resultTwo: " + resultTwo);
    }

    @Test
    public void test10() {
        String flag = stlConfig.getFlag();
        String timestampStr = DateTimeFormatter
                .ofPattern("yyyyMMddHHmmssS")
                .format(LocalDateTime.now());
        MD5 md5 = new MD5();
        String encodeStr = md5.digestHex16(timestampStr);
        Random random = new Random();
        int randomNum = random.nextInt(90) + 10;
        String uid = flag + encodeStr + randomNum;
        System.out.println("生成的融云id: " + uid);
    }

}
