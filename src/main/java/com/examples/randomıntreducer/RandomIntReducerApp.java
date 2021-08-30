package com.examples.randomıntreducer;

import java.util.Random;

public class RandomIntReducerApp {
    public static void main(String[] args) {
        Random r = new Random();


        try (RandomIntReducer randomIntReducer = new RandomIntReducer(0, 99, r,
                10, Integer::sum, System.out::println)) {

           var result =  randomIntReducer.run();
            System.out.println("Result : " + result);

        } catch (Throwable ex) {
            System.err.println(ex.getMessage());
        }

    }
}
