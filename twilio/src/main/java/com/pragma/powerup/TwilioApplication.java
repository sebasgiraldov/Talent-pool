package com.pragma.powerup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@EnableFeignClients
@EnableEurekaClient
@EnableGlobalMethodSecurity(jsr250Enabled=true)
@SpringBootApplication
public class TwilioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwilioApplication.class, args);
	}

}
