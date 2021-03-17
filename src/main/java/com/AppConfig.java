package com;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定义扫描包
 * @author huangxiaofeng
 */
@SpringBootApplication
@MapperScan(annotationClass = Mapper.class, basePackages = "com.mapper")
public class AppConfig {
    final static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(AppConfig.class, args);
            System.out.println("Server startup done.");
        } catch (Exception e) {
            logger.error("服务xxx-support启动报错", e);
        }
    }

}
