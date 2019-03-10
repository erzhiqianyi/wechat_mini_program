package com.erzhiqianyi.mall;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@SpringBootApplication
@EnableJpaAuditing
public class MallApplication {

    @Value("${spring.datasource.maximum-pool-size}")
    private int connectionPoolSize;

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }


    @Bean
    public Scheduler jdbcScheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }
}
