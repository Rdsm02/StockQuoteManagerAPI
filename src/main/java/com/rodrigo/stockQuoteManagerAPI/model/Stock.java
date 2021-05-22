package com.rodrigo.stockQuoteManagerAPI.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "stock_quote")
public class Stock {
	
	@Id
	private String id;
	
	@Transient
	private Map<Date, String> quotes;
	
	@Column(name = "quotes")
	private String quotes_qtd;
	
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

	/**
	 * @return the quotes_qtd
	 */
	public String getQuotes_qtd() {
		return quotes_qtd;
	}

}
