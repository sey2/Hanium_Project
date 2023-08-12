package com.fishing.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest
@EnableJpaAuditing
class FishingFishingApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(FishingFishingApplicationTests.class, args);
	}

}
