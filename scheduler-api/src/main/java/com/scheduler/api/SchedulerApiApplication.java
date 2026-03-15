package com.scheduler.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.scheduler.core.entity")
@EnableScheduling
public class SchedulerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApiApplication.class, args);
    }

}