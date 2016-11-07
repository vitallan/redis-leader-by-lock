package com.allanvital.leaderbylock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RedisLeaderByLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisLeaderByLockApplication.class, args);
	}
}
