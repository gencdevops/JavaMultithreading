package com.examples.semaphore;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1, true);
        MySemaphore taskIncrement = new MySemaphore(true, "Incrementor" , semaphore);
        MySemaphore taskDecrement = new MySemaphore(false, "Decrementor", semaphore);

        Thread tInc = new Thread(taskIncrement);
        Thread tDec = new Thread(taskDecrement);

        tInc.start();
        tDec.start();

        tInc.join();
        tDec.join();
    }
}
