package com.example.producerconsumerwaitnotify.runner;

import com.example.producerconsumerwaitnotify.component.SharedObject;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutorService;

@Component
public class ProducerConsumerRunner implements ApplicationRunner {
    private final ExecutorService threadPool;
    private final SharedObject sharedObject;
    private final Random random;

    private void producerCallback() {
        int i = 0;

        for (; ; ) {
            try {
                Thread.sleep(random.nextInt(500) + 300);
                sharedObject.setVal(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i == 99)
                break;

            ++i;
        }
    }

    private void consumerCallback() {
        int val;

        for(;;) {
            try {
                Thread.sleep(random.nextInt(500) + 300);
                val = sharedObject.getVal();
                System.out.print(val + " ");
                if(val == 99)
                    break;

               ++val;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }

    public ProducerConsumerRunner(SharedObject sharedObject, Random random, ExecutorService threadPool) {
        this.sharedObject = sharedObject;
        this.random = random;
        this.threadPool = threadPool;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        threadPool.execute(this::consumerCallback);
        threadPool.execute(this::producerCallback);
    }
}
