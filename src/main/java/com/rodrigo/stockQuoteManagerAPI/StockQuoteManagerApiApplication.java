package com.rodrigo.stockQuoteManagerAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.rodrigo.stockQuoteManagerAPI" )
public class StockQuoteManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockQuoteManagerApiApplication.class, args);
	}

}
