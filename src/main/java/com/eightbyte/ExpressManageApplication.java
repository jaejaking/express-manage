package com.eightbyte;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yanghaoran@ehomepay.com.cn
 * @returnType
 * @createDate 2019/2/19
 * @description springboot启动入口类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.eightbyte.mapper")
@EnableScheduling
@EnableSwagger2
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class ExpressManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpressManageApplication.class, args);
    }
}
