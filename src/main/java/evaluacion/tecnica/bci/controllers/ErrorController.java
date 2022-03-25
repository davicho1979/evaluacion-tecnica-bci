package evaluacion.tecnica.bci.controllers;

import java.sql.Timestamp;
import java.time.Instant;

import javax.validation.ValidationException;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import evaluacion.tecnica.bci.common.Common;
import evaluacion.tecnica.bci.exceptions.RegisterEmailInvalidException;
import evaluacion.tecnica.bci.models.pojos.GenericResponse;

@ControllerAdvice
public class ErrorController 
{	

	@Autowired
    private Common common;
	
    @ExceptionHandler
    public ResponseEntity<GenericResponse> handleException(MethodArgumentNotValidException e) 
    {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        return ResponseEntity
    			.status(HttpStatus.BAD_REQUEST)
    			.body(
    					GenericResponse.builder()
    					.timestamp(instant.toString())
    					.status(HttpStatus.BAD_REQUEST.value())
    					.error("required_arguments")
    					.message(e.getMessage())
    					.path(common.getPath())
    					.build()
        		);
    }	  
       
   
    @ExceptionHandler
    public ResponseEntity<GenericResponse> handleException(NotReadablePropertyException e) 
    {
        return ResponseEntity
    			.status(HttpStatus.BAD_REQUEST)
    			.body(
    					GenericResponse.builder()
    					.status(HttpStatus.BAD_REQUEST.value())
    					.build()
        		);
    } 
    

    @ExceptionHandler
    public ResponseEntity<GenericResponse> handleException(HttpRequestMethodNotSupportedException exception) 
    {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        return ResponseEntity
    			.status(HttpStatus.METHOD_NOT_ALLOWED)
    			.body(
    					GenericResponse.builder()
    					.timestamp(instant.toString())
    					.status(HttpStatus.METHOD_NOT_ALLOWED.value())
    					.error("method_not_allowed")
    					.message(exception.getMessage())
    					.path(common.getPath())
    					.build()
        		);
    }
    
    @SuppressWarnings("rawtypes")
	@ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity handleHttpMediaTypeNotAcceptableException() 
    {
    	return ResponseEntity
		.status(HttpStatus.BAD_REQUEST)
		.body("{\"data\":null,\"result\":{\"internalCode\":\"400\",\"message\":\"Required header (Accept) does not contain valid values\",\"source\":\"CONSUMER\"}}"); 
    }


    @ExceptionHandler
    public ResponseEntity<GenericResponse> handleException(HttpMediaTypeException exception) 
    {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        return ResponseEntity
    			.status(HttpStatus.BAD_REQUEST)
    			.body(
    					GenericResponse.builder()
    					.timestamp(instant.toString())
    					.status(HttpStatus.BAD_REQUEST.value())
    					.message(exception.getMessage())
    					.path(common.getPath())
    					.build()
        		);
    }
    
    @ExceptionHandler
    public ResponseEntity<GenericResponse> handleException(ValidationException exception) 
    {  	
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        
    	if (exception.getCause().getCause().getClass().equals(NotReadablePropertyException.class))
    	{
            return ResponseEntity
        			.status(HttpStatus.BAD_REQUEST)
        			.body(
        					GenericResponse.builder()
        					.timestamp(instant.toString())
        					.status(HttpStatus.BAD_REQUEST.value())
        					.error("required_arguments")
        					.message(exception.getCause().getCause().getMessage())
        					.path(common.getPath())
        					.build()
            		);
    	} 
    	
        return ResponseEntity
    			.status(HttpStatus.BAD_REQUEST)
    			.body(
    					GenericResponse.builder()
    					.status(HttpStatus.BAD_REQUEST.value())
    					.build()
        		);
   
    	//if (exception.getCause().getCause().getClass() == SecurityTypePasswordValuesException.class)

    }  

    
    @ExceptionHandler
    public ResponseEntity<GenericResponse> handleException(MissingRequestHeaderException exception) 
    {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        return ResponseEntity
    			.status(HttpStatus.BAD_REQUEST)
    			.body(
    					GenericResponse.builder()
    					.status(HttpStatus.BAD_REQUEST.value())
    					.message(exception.getMessage())
    					.timestamp(instant.toString())
    					.path(common.getPath())
    					.build()
        		);
    }
    
    @ExceptionHandler
    public ResponseEntity<GenericResponse> handleException(JpaSystemException exception) 
    {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        return ResponseEntity
    			.status(HttpStatus.BAD_REQUEST)
    			.body(
    					GenericResponse.builder()
    					.status(HttpStatus.BAD_REQUEST.value())
    					.message(exception.getMessage())
    					.timestamp(instant.toString())
    					.path(common.getPath())
    					.build()
        		);
    }    
    
    @ExceptionHandler
    public ResponseEntity<GenericResponse> handleException(RegisterEmailInvalidException exception) 
    {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        return ResponseEntity
    			.status(HttpStatus.BAD_REQUEST)
    			.body(
    					GenericResponse.builder()
    					.status(HttpStatus.BAD_REQUEST.value())
    					.message(exception.getMessage())
    					.timestamp(instant.toString())
    					.path(common.getPath())
    					.build()
        		);
    }     
    
    @ExceptionHandler
    public ResponseEntity<GenericResponse> handleException(Exception exception) 
    {  	
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        return ResponseEntity
    			.status(HttpStatus.BAD_REQUEST)
    			.body(
    					GenericResponse.builder()
    					.status(HttpStatus.BAD_REQUEST.value())
    					.message(exception.getMessage())
    					.timestamp(instant.toString())
    					.path(common.getPath())    					
    					.build()
        		);
   
    	//if (exception.getCause().getCause().getClass() == SecurityTypePasswordValuesException.class)

    }      
}
