package com.allanvital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PocRedisLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocRedisLockApplication.class, args);
	}
}
