package com.rodrigo.stockQuoteManagerAPI.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rodrigo.stockQuoteManagerAPI.exceptions.StockQuoteRepositoryException;
import com.rodrigo.stockQuoteManagerAPI.exceptions.StockQuoteServiceException;
import com.rodrigo.stockQuoteManagerAPI.model.Stock;

public interface StockQuoteService {

	Stock getStockQuoteById(String id) throws JsonMappingException, JsonProcessingException, StockQuoteRepositoryException;

	void createStockQuote(Stock stock) throws JsonMappingException, JsonProcessingException, StockQuoteRepositoryException, StockQuoteServiceException;

	List<Stock> getAllStockQuoteById() throws JsonMappingException, JsonProcessingException, StockQuoteRepositoryException;

}
