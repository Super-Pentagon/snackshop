package com.qcy.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author CodeHunter_qcy
 * @date 2020/6/20 - 18:29
 */
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"com.qcy"})
public class BuyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuyApplication.class,args);
    }
}
