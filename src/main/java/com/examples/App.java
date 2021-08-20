package com.examples;

import com.examples.threadutil.ThreadUtil;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        RandomIntGenerator rgt = new RandomIntGenerator(10, 0, 99);
        Thread t1 = new Thread(rgt, "MyThread");
        t1.start();

    }
}


class RandomIntGenerator implements Runnable {
    private final int count;
    private final int min;
    private final int max;


    public RandomIntGenerator(int count, int min, int max) {

        this.count = count;
        this.max = max;
        this.min = min;
    }


    @Override
    public void run() {
        Random r = new Random();
        Thread self = Thread.currentThread();
        System.out.println("thread adı: " + self.getName());
        for (int i = 0; i < count; i++) {
            int val = r.nextInt(max - min) + min;
            System.out.printf("%02d ", val);
            ThreadUtil.sleep(1000);

        }
        System.out.println();
    }

}