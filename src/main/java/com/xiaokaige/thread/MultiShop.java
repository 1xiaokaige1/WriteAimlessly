package com.xiaokaige.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author zengkai
 * @date 2021/6/26 10:56
 */
public class MultiShop {

    public static final Executor executor = Executors.newFixedThreadPool(2, r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println(findPrices("myPhone"));
        long duration = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Done in " + duration + " msecs");
    }

    public static List<String> findPrices(String product) {
        List<Shop> shopList = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll")
        );
        /*List<String> list = shopList.
                stream().
                map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).
                collect(Collectors.toList());*/
        /*List<String> list = shopList.
                parallelStream().
                map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).
                collect(Collectors.toList());*/

        List<CompletableFuture<String>> futureList = shopList
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)),executor))
                .collect(Collectors.toList());
        return futureList.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
