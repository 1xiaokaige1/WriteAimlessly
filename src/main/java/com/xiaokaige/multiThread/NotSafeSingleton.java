package com.xiaokaige.multiThread;

/**
 * @author zengkai
 * @date 2021/7/24 15:08
 */
public class NotSafeSingleton {

    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();
        new Thread(() -> {
            System.out.println("animalOne:" + animalFactory.getInstance());
        }).start();
        new Thread(() -> {
            System.out.println("animalTwo:" + animalFactory.getInstance());
        }).start();
    }

}


class AnimalFactory {
    private Animal animal = null;

    public Animal getInstance() {
        if (animal == null) {
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            animal = new Animal();
        }
        return animal;
    }
}

class Animal {

}
