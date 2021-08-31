package com.example.producerconsumerwaitnotify.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorServiceConfig {


    @Bean
    public ExecutorService getExecutorService(@Value("${thread.count:-1}") int count) {
        return count == -1 ? Executors.newCachedThreadPool() : Executors.newFixedThreadPool(count);
    }


}
