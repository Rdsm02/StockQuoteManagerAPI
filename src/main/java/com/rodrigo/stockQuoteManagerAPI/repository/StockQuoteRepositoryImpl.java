package com.rodrigo.stockQuoteManagerAPI.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigo.stockQuoteManagerAPI.exceptions.StockQuoteRepositoryException;
import com.rodrigo.stockQuoteManagerAPI.model.Stock;


@Repository
public class StockQuoteRepositoryImpl implements StockQuoteRepository {
	
	@PersistenceContext(unitName = "dataSource")
	private EntityManager entityManager;
	
	private final static String INSERT_STOCK_QUOTE = "INSERT INTO bootdb.stock_quote (id, quotes) "
			+ "VALUES(?1, ?2) "
			+ "ON DUPLICATE KEY UPDATE "
			+ "quotes = ?2 ";
	
	private final static String SELECT_STOCK_QUOTE = "SELECT id, quotes "
			+ "FROM bootdb.stock_quote "
			+ "WHERE 1 = 1 ";

	
	@Override
	@Transactional(transactionManager = "dataSourceTransactionManager")
	public void createStockQuote(Stock stock) throws JsonProcessingException {
		Query query = entityManager.createNativeQuery(INSERT_STOCK_QUOTE);
		
		ObjectMapper mapper = new ObjectMapper();
		String quotesString = mapper.writeValueAsString(stock.getQuotes());	
		
		query.setParameter(1, stock.getId())
		.setParameter(2, quotesString);
		
		query.executeUpdate();
	}
	
	@Override
	public Stock getStockQuoteById(String id) throws JsonMappingException, JsonProcessingException, StockQuoteRepositoryException {
		Query query = entityManager.createNativeQuery(SELECT_STOCK_QUOTE + " AND id = ?1 ");
		
		query.setParameter(1, id);
		
		try {
			Object[] row = (Object[]) query.getSingleResult();	
			ObjectMapper mapper = new ObjectMapper();
			Map<Date, String> quotes = mapper.readValue((String)row[1], new TypeReference<LinkedHashMap<Date, String>>() {});
			//quotes.entrySet().stream().map(q -> Utils.parseDateToString(q.getKey()));			
			return new Stock((String)row[0], quotes);
			
		} catch (NoResultException e) {
			throw new StockQuoteRepositoryException("Sem resultados");
		}	
		
		
	}
	
	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Stock> getAllStockQuote() throws JsonMappingException, JsonProcessingException, StockQuoteRepositoryException {
		Query query = entityManager.createNativeQuery(SELECT_STOCK_QUOTE);
		
		try {
			List<Object[]> rows = (List<Object[]>) query.getResultList();	
			if (rows.isEmpty()) {
				throw new StockQuoteRepositoryException("Sem resultados");
			}
			
			List<Stock> stockList = new ArrayList<>(rows.size());
			ObjectMapper mapper = new ObjectMapper();
			for (Object[] row : rows) {
				Map<Date, String> quotes = mapper.readValue((String)row[1], new TypeReference<LinkedHashMap<Date, String>>() {});
				stockList.add(new Stock((String)row[0], quotes));
			}			
			//quotes.entrySet().stream().map(q -> Utils.parseDateToString(q.getKey()));			
			return stockList;
			
		} catch (NoResultException e) {
			throw new StockQuoteRepositoryException("Sem resultados");
		}	
		
		
	}
}
