package com.rodrigo.stockQuoteManagerAPI.repository;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rodrigo.stockQuoteManagerAPI.exceptions.StockQuoteRepositoryException;
import com.rodrigo.stockQuoteManagerAPI.model.Stock;

public interface StockQuoteRepository {

	void createStockQuote(Stock stock) throws JsonProcessingException;

	Stock getStockQuoteById(String id) throws JsonMappingException, JsonProcessingException, StockQuoteRepositoryException;

	List<Stock> getAllStockQuote() throws JsonMappingException, JsonProcessingException, StockQuoteRepositoryException;

}
