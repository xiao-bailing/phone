package com.qlu.phone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qlu.phone.Mapper")
public class UpdatephoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpdatephoneApplication.class, args);
    }

}
