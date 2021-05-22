package com.rodrigo.stockQuoteManagerAPI.enums;

public enum CacheEnum {
	
	STOCK_CACHE("stockCache");
	
	CacheEnum(String string) {
		
	}

	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	

}
