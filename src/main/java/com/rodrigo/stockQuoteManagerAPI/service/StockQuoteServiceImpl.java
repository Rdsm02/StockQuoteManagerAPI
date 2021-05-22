package com.rodrigo.stockQuoteManagerAPI.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rodrigo.stockQuoteManagerAPI.exceptions.StockQuoteRepositoryException;
import com.rodrigo.stockQuoteManagerAPI.exceptions.StockQuoteServiceException;
import com.rodrigo.stockQuoteManagerAPI.model.Stock;
import com.rodrigo.stockQuoteManagerAPI.repository.StockQuoteRepository;

@Service
public class StockQuoteServiceImpl implements StockQuoteService {
	
	private StockQuoteRepository stockQuoteRepository;
	private StockManagerAPI stockManagerAPI;
	
	/**
	 * @param stockQuoteRepository
	 */
	public StockQuoteServiceImpl(StockQuoteRepository stockQuoteRepository,
			StockManagerAPI stockManagerAPI) {
		this.stockQuoteRepository = stockQuoteRepository;
		this.stockManagerAPI = stockManagerAPI;
	}

	@Override
	public void createStockQuote(Stock stock) throws JsonMappingException, JsonProcessingException, StockQuoteServiceException {
		
		
		if (!isRegistered(stock)) {
			throw new StockQuoteServiceException(stock.getId() + " not registered!");
		}		
		
		Stock stockQuoteById = null;
		try {
			stockQuoteById = stockQuoteRepository.getStockQuoteById(stock.getId());
		} catch (JsonProcessingException | StockQuoteRepositoryException e) {			
			stockQuoteRepository.createStockQuote(stock);				
		}		
		
		for (Entry<Date, String> entry : stock.getQuotes().entrySet()) {
			stockQuoteById.getQuotes().compute(entry.getKey(), (id, quote ) -> {
				quote = entry.getValue();
				return quote;
			});
		}		
		
		stockQuoteRepository.createStockQuote(stock);	
		
	}

	@Override
	public Stock getStockQuoteById(String id) throws JsonMappingException, JsonProcessingException, StockQuoteRepositoryException {
		return stockQuoteRepository.getStockQuoteById(id);
	}
	
	@Override
	public List<Stock> getAllStockQuoteById() throws JsonMappingException, JsonProcessingException, StockQuoteRepositoryException {
		return stockQuoteRepository.getAllStockQuote();
	}
	
	private boolean isRegistered(Stock stock) {
		Map<String, String> stockRegistered = stockManagerAPI.getStockRegistered();
		return stockRegistered.containsKey(stock.getId());
	}
}
