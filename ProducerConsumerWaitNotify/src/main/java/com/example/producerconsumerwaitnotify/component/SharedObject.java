package com.example.producerconsumerwaitnotify.component;

import org.springframework.stereotype.Component;

@Component
public class SharedObject {
    private int val;
    private volatile boolean produce = true;

    public synchronized void setVal(int val) {
        while (!produce) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.val = val;
        produce = false;
        this.notify();
    }

    public synchronized int getVal() throws InterruptedException {
        while (produce) {
            this.wait();
        }
        int val = this.val;
        this.produce = true;
        this.notify();
        return val;

    }


}
