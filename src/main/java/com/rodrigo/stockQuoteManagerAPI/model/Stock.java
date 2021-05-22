package com.rodrigo.stockQuoteManagerAPI.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {
	
	@Id
	private String id;
	
	@Column
	@ElementCollection
	private Map<Date, String> quotes;
	
	/**
	 * @param id
	 * @param quotes
	 */
	public Stock(String id, Map<Date, String> quotes) {
		super();
		this.id = id;
		this.quotes = quotes;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the quotes
	 */
	public Map<Date, String> getQuotes() {
		return quotes;
	}

}
