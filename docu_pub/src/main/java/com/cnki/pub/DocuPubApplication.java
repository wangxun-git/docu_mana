package com.cnki.pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.cnki")
public class DocuPubApplication {

    public static void main(String[] args) {

        SpringApplication.run(DocuPubApplication.class, args);

    }

}
