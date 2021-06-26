package com.xiaokaige.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author zengkai
 * @date 2021/6/26 10:30
 */
@Data
@AllArgsConstructor
public class Shop {

    private String name;

    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long startTime = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Invocation returned after" + invocationTime + " msecs");

        try {
            Double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }


    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        //int i = 1/0;
        return Math.random() * product.charAt(0) + product.charAt(1);
    }

    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Future<Double> getPriceAsync(String product) {
        /*CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();

        return futurePrice;*/
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }




}
