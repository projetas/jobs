package br.com.rest.veiculo.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.rest.veiculo.exceptions.ErrorDetails;
import br.com.rest.veiculo.exceptions.ResourceNotFoundDetails;
import br.com.rest.veiculo.exceptions.ResourceNotFoundException;
import br.com.rest.veiculo.exceptions.ValidationErrorDetails;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException) {
		ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder.builder()
				.withTimestamp(new Date().getTime())
				.withStatus(HttpStatus.NOT_FOUND.value())
				.withTitle("Resource not found")
				.withDetail(rnfException.getMessage())
				.withDeveloperMessage(rnfException.getClass().getName())
				.build();
		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException, 
														HttpHeaders headers, HttpStatus status, 
														WebRequest request) {
		
		List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		
		ValidationErrorDetails verDetails = ValidationErrorDetails.Builder.builder()
				.withTimestamp(new Date().getTime())
				.withStatus(HttpStatus.BAD_REQUEST.value())
				.withTitle("Field Validation Error")
				.withDetail("Field Validation Error")
				.withDeveloperMessage(manvException.getClass().getName())
				.withField(fields)
				.withFieldMessage(fieldMessages)
				.build();
		return new ResponseEntity<>(verDetails, HttpStatus.BAD_REQUEST);
	}
	
	// This is the generic Exception handler of Spring inside the ResponseEntityExceptionHandler.class
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
																HttpHeaders headers, HttpStatus status, 
																WebRequest request) {

		ErrorDetails errorDetails = ErrorDetails.Builder.builder()
				.withTimestamp(new Date().getTime())
				.withStatus(status.value())
				.withTitle("Internal Exception")
				.withDetail(ex.getMessage())
				.withDeveloperMessage(ex.getClass().getName())
				.build();
		return new ResponseEntity<>(errorDetails, headers, status);
	}
}
