package org.car.example;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
