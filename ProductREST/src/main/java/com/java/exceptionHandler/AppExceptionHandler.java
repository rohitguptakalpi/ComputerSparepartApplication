package com.java.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.java.exceptions.ProductNotFoundException;
import com.java.exceptions.SparepartNotFoundException;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String, String> errorMap =new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ProductNotFoundException.class)
	public Map<String, String> handleBusinsessExceptionForProduct(ProductNotFoundException ex){
		Map<String, String> errorMap =new HashMap<String, String>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SparepartNotFoundException.class)
	public Map<String, String> handleBusinsessExceptionForSparepart(SparepartNotFoundException ex){
		Map<String, String> errorMap =new HashMap<String, String>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}

		
}
