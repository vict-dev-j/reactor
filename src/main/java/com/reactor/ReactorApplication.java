package com.reactor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ReactorApplication {

    private static final Logger log = LogManager.getLogger(ReactorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ReactorApplication.class, args);
        log.info("\n^^^^^^^^^^^^\n Reactor multithreaded \n^^^^^^^^^^^^\n");
    }
}
