package br.com.vehicle.api.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.vehicle.support.exception.VehicleException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
	private final MessageSource messageSource;

	@Autowired
	public RestExceptionHandler(MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

	@Override
	@ExceptionHandler(VehicleException.class)
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		if (!Exception.class.isAssignableFrom(VehicleException.class))
		{

			return super.handleExceptionInternal(ex, body, headers, status, request);
		}

		VehicleException vex = (VehicleException) ex;

		if (vex.getMessages() == null || vex.getMessages().isEmpty())
		{
			return super.handleExceptionInternal(ex, body, headers, status, request);
		}

		vex.getMessages().stream().forEach(m -> {

			String i18nText = messageSource.getMessage(m.i18n.getCode(), m.args, request.getLocale());
			if (i18nText == null)
			{
				m.message = String.format(m.i18n.getDefaultMessage(), m.args);
			}
		});

		return new ResponseEntity<>(vex.getMessages(), HttpStatus.EXPECTATION_FAILED);
	}
}