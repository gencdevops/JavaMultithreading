package com.examples.semaphore;

import java.util.concurrent.Semaphore;

public class MySemaphore implements Runnable {
    private boolean increment;
    private String name;
    private Semaphore semaphore;

    public MySemaphore(boolean increment, String name, Semaphore semaphore) {
        this.increment = increment;
        this.name = name;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            semaphore.acquire();
            if (increment) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(name + " value : " + ShareObject.incrementAndGet());
                    Thread.sleep(1000);
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    System.out.println(name + " value : " + ShareObject.decrementAndGet());
                    Thread.sleep(1000);
                }
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        semaphore.release();
    }


}
