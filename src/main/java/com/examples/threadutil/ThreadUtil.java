package com.examples.threadutil;

public final class ThreadUtil {
    private ThreadUtil() {}


    public static void sleep(long miliseconds) {
        try{
            Thread.sleep(miliseconds);
        } catch (InterruptedException ignore) {

        }
    }

}
