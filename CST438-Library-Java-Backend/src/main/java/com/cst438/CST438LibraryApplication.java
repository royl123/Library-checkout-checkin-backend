package com.cst438;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CST438LibraryApplication  {

	public static void main(String[] args) throws InterruptedException {
		// delay for 1 minute to allow time for Docker compose to start database service.
		// Thread.sleep(60000); 
		SpringApplication.run(CST438LibraryApplication.class, args);
	}

}
