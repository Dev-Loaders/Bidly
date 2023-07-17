package com.bidly.bidly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BidlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidlyApplication.class, args);
	}

}
