package com.examples.randomıntreducer;

import java.util.OptionalInt;
import java.util.Random;

public class RandomIntReducerApp {
    public static void main(String[] args) {
        Random r = new Random();
        RandomIntReducer randomIntReducer =
                new RandomIntReducer()
                .setRandom(r)
                .setCount(10)
                .setMax(99)
                .setReduceCallback(Integer::sum)
                .setValueCallback(System.out::println);

        try (randomIntReducer) {

           OptionalInt resultOpt =  randomIntReducer.run();
           resultOpt.ifPresentOrElse(System.out::println,
                   () -> System.out.println("Problem occurs"));

        } catch (Throwable ex) {
            System.err.println(ex.getMessage());
        }

    }
}
