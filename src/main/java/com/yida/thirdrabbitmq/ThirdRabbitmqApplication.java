package com.yida.thirdrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.yida.thirdrabbitmq.dao") //一定是通用mapper的
public class ThirdRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdRabbitmqApplication.class, args);
    }

}
