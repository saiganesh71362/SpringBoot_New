package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserManagement1Application {

	public static void main(String[] args) {
		SpringApplication.run(UserManagement1Application.class, args);
	}

}
