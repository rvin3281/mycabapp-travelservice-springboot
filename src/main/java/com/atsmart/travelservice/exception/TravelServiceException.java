package com.atsmart.travelservice.exception;

public class TravelServiceException extends RuntimeException {

	public TravelServiceException(String message) {
		super(message);
	}
	

	
	public TravelServiceException(String message, Throwable cause) {
		super(message,cause);
	}
}
