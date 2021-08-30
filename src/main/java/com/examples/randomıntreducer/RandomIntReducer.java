package com.examples.randomıntreducer;

import java.io.Closeable;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;


public class RandomIntReducer implements Closeable {
    private final ExecutorService threadPool;
    private final int min;
    private final int max;
    private final Random random;
    private int result;
    private boolean done;
    private final int count;
    private final IntBinaryOperator binaryOperator;
    private final IntConsumer intConsumer;

    private void reduceCallback() {
        result = 0;
        for (int i = 0; i < count; ++i) {
            int val = random.nextInt(max - min + 1) + min;
            intConsumer.accept(val);

            result = binaryOperator.applyAsInt(result, val);

        }
    }

    public RandomIntReducer(int min, int max, Random random, int count,
                            IntBinaryOperator binaryOperator, IntConsumer intConsumer) {

        this.min = min;
        this.max = max;
        this.random = random;
        threadPool = Executors.newSingleThreadExecutor();
        this.count = count;
        this.binaryOperator = binaryOperator;
        this.intConsumer = intConsumer;
    }

    public boolean isDone() {
        return done;
    }


    public int run() throws Exception {
        var future = threadPool.submit(this::reduceCallback);

        future.get();

        return result;

    }


    @Override
    public void close() throws IOException {
        if (!threadPool.isShutdown())
            threadPool.shutdown();
    }

}
