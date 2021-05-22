package com.rodrigo.stockQuoteManagerAPI.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rodrigo.stockQuoteManagerAPI.exceptions.StockQuoteRepositoryException;
import com.rodrigo.stockQuoteManagerAPI.exceptions.StockQuoteServiceException;
import com.rodrigo.stockQuoteManagerAPI.model.Stock;
import com.rodrigo.stockQuoteManagerAPI.service.StockQuoteService;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private StockQuoteService stockQuoteService;
	
	public StockController(StockQuoteService stockQuoteService) {
		this.stockQuoteService = stockQuoteService;
	}

	@RequestMapping(value = "/quote", method = RequestMethod.POST)
	public ResponseEntity<Object> createStockQuote(@RequestBody Stock stock) {
		try {
			stockQuoteService.createStockQuote(stock);
			return ResponseEntity.ok().build();
		} catch (JsonProcessingException | StockQuoteRepositoryException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (StockQuoteServiceException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		} 
	}
	
	@RequestMapping(value = "/quote", method = RequestMethod.GET)
	public ResponseEntity<Stock> getStockQuoteById(@RequestParam(name = "id") String id) {
		
		Stock stockQuoteById;
		try {
			stockQuoteById = stockQuoteService.getStockQuoteById(id);
		} catch (JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (StockQuoteRepositoryException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.ok(stockQuoteById);
	}
	
	@RequestMapping(value = "/quote/all", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllStockQuote() {
		List<Stock> stockQuoteList;
		try {
			stockQuoteList = stockQuoteService.getAllStockQuoteById();
		} catch (JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (StockQuoteRepositoryException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.ok(stockQuoteList);
	}
	

}
