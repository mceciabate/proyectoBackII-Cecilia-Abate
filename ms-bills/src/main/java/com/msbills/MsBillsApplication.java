package com.msbills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
public class MsBillsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBillsApplication.class, args);
    }

}
