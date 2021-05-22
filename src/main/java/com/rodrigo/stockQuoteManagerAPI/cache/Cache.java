package com.rodrigo.stockQuoteManagerAPI.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.rodrigo.stockQuoteManagerAPI.enums.CacheEnum;

@Service
public class Cache {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final CacheManager cacheManager;
	
	/**
	 * @param cacheManager
	 */
	public Cache(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void clearStockcache(CacheEnum cacheEnum) {
		cacheManager.getCache(cacheEnum.getName()).clear();
		logger.info("LOGGER AS CLEAR::LOGNAME -> " + cacheEnum.name());	
	}
	
}
