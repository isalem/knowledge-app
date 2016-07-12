package com.ness.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Knowledge not found.")
public class KnowledgeNotFountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5558726110036986986L;


}
