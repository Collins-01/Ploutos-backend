package com.collins.ploutos.ploutos;

import com.collins.ploutos.ploutos.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class PloutosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PloutosApplication.class, args);
    }
}
