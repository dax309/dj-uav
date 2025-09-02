package com.cleaner.djuav;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cleaner.djuav.mapper")
public class DjUavApplication {

    public static void main(String[] args) {
        SpringApplication.run(DjUavApplication.class, args);
    }

}
