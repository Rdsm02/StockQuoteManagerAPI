package com.rodrigo.stockQuoteManagerAPI.exceptions;

public class StockQuoteServiceException extends Exception {

	private static final long serialVersionUID = -2422390764171345799L;

	public StockQuoteServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public StockQuoteServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public StockQuoteServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public StockQuoteServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public StockQuoteServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
