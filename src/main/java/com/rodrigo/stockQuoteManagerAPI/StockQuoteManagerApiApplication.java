package com.rodrigo.stockQuoteManagerAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StockQuoteManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockQuoteManagerApiApplication.class, args);
	}

}
