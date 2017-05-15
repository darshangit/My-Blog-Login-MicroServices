package com.angular.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Darsh on 5/8/2017.
 */

@SpringBootApplication
@ComponentScan
public class ServicesMain {
    public static void main(String[] args) {
        SpringApplication.run(ServicesMain.class, args);
    }
}