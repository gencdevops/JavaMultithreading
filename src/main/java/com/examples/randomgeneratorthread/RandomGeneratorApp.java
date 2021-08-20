package com.examples.randomgeneratorthread;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

//4 tane dosyaya her biri 0 ve 99 aralığında 10 tane sayı yazsın ve dosya uzantısını da yazsın
public class RandomGeneratorApp {
    private RandomGeneratorApp() {
    }
    private static void doWorkForFile(Path path, int count, int min, int max) {
        Random r = new Random();
        try (BufferedWriter br = Files.newBufferedWriter(path,
                StandardCharsets.UTF_8, StandardOpenOption.APPEND,
                StandardOpenOption.CREATE)) {

            for (int k = 0; k < count; ++k) {
                br.write((r.nextInt(max - min) + min) + "\r\n");
                br.flush();
            }


        } catch (IOException ex) {
            System.err.println("IO problem");
        }
    }

    private static void runnableCallback(int count, int min, int max, String filePrefix, int suffix) {

        Thread self = Thread.currentThread();
        String fullName = String.format("%s_%s_%s", filePrefix, self.getName(), suffix);
        doWorkForFile(Path.of(fullName) , count, min, max);
    }
    private static void doGenerate(String[] args) {
        try {

            int count = Integer.parseInt(args[0]);
            int min = Integer.parseInt(args[1]);
            int max = Integer.parseInt(args[2]);
            int numberOfFiles = Integer.parseInt(args[3]);
            String filePrefix = args[4];

            for (int i = 0; i < numberOfFiles; i++) {
                int suffix = i + 1;
                new Thread(() -> runnableCallback(count,min,max,filePrefix,suffix)).start();
            }


        } catch (NumberFormatException ignore) {
            System.err.println("Invalid arguments");
        }
    }

    public static void run(String[] args) {
        String erorMessage = "usage: java -jar RandomGeneratorPersistanceApp <numberCount> <min> <max> <fileCount> <filePrefix>";

        if (args.length != 5) {
            System.err.println(erorMessage);
            System.exit(1);
        }

        doGenerate(args);
    }

    public static void main(String[] args) {
        doGenerate(args);
    }

}
