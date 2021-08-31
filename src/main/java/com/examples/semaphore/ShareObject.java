package com.examples.semaphore;

public class ShareObject {
    private static int value = 0;

    static int incrementAndGet() {
        return ++value;
    }

    static int decrementAndGet() {
        return --value;
    }
}
