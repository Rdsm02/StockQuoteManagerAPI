package com.rodrigo.stockQuoteManagerAPI;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.rodrigo.stockQuoteManagerAPI.service.StockManagerAPI;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StockManagerAPI stockManagerAPI;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Map<String, String> stockRegistered = stockManagerAPI.getStockRegistered();
		logger.info("List of stocks load with success::size -->> " + stockRegistered.size());
	}

}
