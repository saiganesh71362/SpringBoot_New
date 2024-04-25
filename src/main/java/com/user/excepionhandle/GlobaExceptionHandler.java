package com.user.excepionhandle;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Controller
@ControllerAdvice
public class GlobaExceptionHandler 
{
	

	@ExceptionHandler(value=NoUserIdException.class)
	public ResponseEntity<ApiError> handleNoUserIdException( Exception exception , WebRequest request)
	{
		ApiError error = new ApiError(exception.getMessage(),request.getDescription(false) , new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.NOT_FOUND);
		 
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<ApiError> handleUserNotFoundException(Exception exception,WebRequest request)
	{
		ApiError error = new ApiError( exception.getMessage(),request.getDescription(false),new Date() );
		
		return new ResponseEntity<ApiError>(error,HttpStatus.NOT_FOUND);
	}
}
