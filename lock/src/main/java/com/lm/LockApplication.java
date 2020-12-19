package com.lm;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class LockApplication {

    public static void main(String[] args) {
//        SpringApplication.run(LockApplication.class, args);
        Random random = new Random();
        System.out.println(random.nextInt(100000000));

    }

}
