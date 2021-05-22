package com.rodrigo.stockQuoteManagerAPI.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockManagerAPIImpl  implements StockManagerAPI {
	
	private Logger logger = LoggerFactory.getLogger(StockManagerAPI.class);
	
	private final RestTemplate restTemplate;
	
	private final String url;

	public StockManagerAPIImpl(@Value("${stock.manager.api.url}") String url) {
		this.restTemplate = new RestTemplate();
		this.url = url;
	}
	
	@Override
	@Cacheable(value = "stockCache", unless = "#result == null or #result.size() == 0")
	public Map<String, String> getStockRegistered() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
		
		ResponseEntity<List<Map<String, String>>> stoksList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Map<String, String>>>(){});
		List<Map<String, String>> body = stoksList.getBody();
		
		Map<String, String> stoksMap = new HashMap<>(body.size());
		for (Map<String, String> stock : body) {			
			stoksMap.put(stock.get("id"), stock.get("description"));
		}			
		
		return stoksMap;
	}

	
}
