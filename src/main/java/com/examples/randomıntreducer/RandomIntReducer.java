package com.examples.randomıntreducer;

import java.io.Closeable;
import java.io.IOException;
import java.util.OptionalInt;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;


public class RandomIntReducer implements Closeable {
    private static int DEFAULT_COUNT = 10;
    private final ExecutorService threadPool;
    private int min;
    private int max;
    private Random random;
    private int result;
    private boolean done;
    private long count;
    private IntBinaryOperator binaryOperator;
    private IntConsumer intConsumer;

    private void reduceCallback() {
        result = 0;
        for (int i = 0; i < count; ++i) {
            int val = random.nextInt(max - min + 1) + min;
            if (intConsumer != null) {
                intConsumer.accept(val);
            }

            result = binaryOperator.applyAsInt(result, val);

        }
    }

    public RandomIntReducer() {
        random = new Random();
        max = 1;
        count = DEFAULT_COUNT;
        threadPool = Executors.newSingleThreadExecutor();
    }

    public RandomIntReducer setRandom(Random random) {
        this.random = random;
        return this;
    }

    public RandomIntReducer setMin(int min) {
        this.min = min;
        return this;
    }

    public RandomIntReducer setMax(int max) {
        this.max = max;
        return this;
    }

    public RandomIntReducer setCount(long count) {
        this.count = count;
        return this;

    }

    public RandomIntReducer setReduceCallback(IntBinaryOperator intBinaryOperator) {
        this.binaryOperator = intBinaryOperator;
        return this;
    }

    public RandomIntReducer setValueCallback(IntConsumer intConsumer) {
        this.intConsumer = intConsumer;
        return this;
    }

    public boolean isDone() {

        return done;
    }


    public OptionalInt run()  {
        if (binaryOperator == null) {
            return OptionalInt.empty();
        }

        var future = threadPool.submit(this::reduceCallback);
        try {
            future.get();
        }catch (Throwable ex) {
            throw new RuntimeException("Problem while waitimg result", ex);
        }
        return OptionalInt.of(result);
    }


    @Override
    public void close() throws IOException {
        if (!threadPool.isShutdown())
            threadPool.shutdown();
    }

}
