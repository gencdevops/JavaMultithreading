package com.examples.ticktockapp;

public class Main {
    public static void main(String[] args) {
        MessageGenerator generator = new MessageGenerator();

        MyThread thread1 = new MyThread("Tick", generator);
        MyThread thread2 = new MyThread("Tock", generator);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread is interrupted");
        }
        System.out.println("Main thread is finishing");
    }
    }

