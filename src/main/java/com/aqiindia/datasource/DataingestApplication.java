package com.aqiindia.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DataingestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataingestApplication.class, args);
	}

}
