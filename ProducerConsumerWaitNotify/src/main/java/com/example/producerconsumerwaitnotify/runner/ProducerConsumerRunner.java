package com.example.producerconsumerwaitnotify.runner;

import com.example.producerconsumerwaitnotify.component.SharedObject;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ProducerConsumerRunner implements ApplicationRunner {
    private final SharedObject sharedObject;
    private final Random random;

    private void producerCallback() {

    }

    public ProducerConsumerRunner(SharedObject sharedObject, Random random) {
        this.sharedObject = sharedObject;
        this.random = random;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
