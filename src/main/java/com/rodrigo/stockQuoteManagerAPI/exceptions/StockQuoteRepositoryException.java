package com.rodrigo.stockQuoteManagerAPI.exceptions;

public class StockQuoteRepositoryException extends Exception {

	private static final long serialVersionUID = 4504688467769925359L;

	public StockQuoteRepositoryException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public StockQuoteRepositoryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public StockQuoteRepositoryException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public StockQuoteRepositoryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public StockQuoteRepositoryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
