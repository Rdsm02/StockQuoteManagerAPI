package com.rodrigo.stockQuoteManagerAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.stockQuoteManagerAPI.cache.Cache;
import com.rodrigo.stockQuoteManagerAPI.enums.CacheEnum;

@RestController
public class CacheController {	
	
	private final Cache cache;

	public CacheController(Cache cache) {
		this.cache = cache;
	}
	
	@RequestMapping(value = "stockcache", method = RequestMethod.DELETE)
	public void clearStockcache() {
		cache.clearStockcache(CacheEnum.STOCK_CACHE);
	}

}
