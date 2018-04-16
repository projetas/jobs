package br.com.vehicle.api.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.vehicle.model.I18nMessage;
import br.com.vehicle.support.exception.FailCode;
import br.com.vehicle.support.exception.VehicleException;
import br.com.vehicle.support.handler.I18nHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
	@Autowired
	private I18nHandler i18n;

	@ExceptionHandler(VehicleException.class)
	protected ResponseEntity<?> handleExceptionInternal(RuntimeException ex, WebRequest request)
	{
		Locale locale = request.getLocale();
		String sysError = i18n.getMessage(FailCode.SYS_ERROR, locale);
		if (!ex.getClass().isAssignableFrom(VehicleException.class))
		{
			return super.handleExceptionInternal(ex, sysError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
		}

		VehicleException vex = (VehicleException) ex;

		if (vex.getMessages() == null || vex.getMessages().isEmpty())
		{
			return super.handleExceptionInternal(ex, sysError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
		}

		vex.getMessages().stream().forEach(m -> {

			if (m.getMessage() != null && !m.getMessage().isEmpty())
			{
				m.setMessage(i18n.getMessage(m.getMessage(), locale, m.getArgs()));
				return;
			}

			Object[] args = internationalizeArgs(m.getArgs(), locale);
			m.setMessage(i18n.getMessage(m.getI18n(), locale, args));
			if (m.getMessage() == null || m.getMessage().isEmpty())
			{
				m.setMessage(String.format(m.getI18n().getDefaultMessage(), m.getArgs()));
			}
		});

		return new ResponseEntity<>(vex.getMessages(), HttpStatus.EXPECTATION_FAILED);
	}

	// @ExceptionHandler
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	// public ResponseEntity<?> handleException(MethodArgumentNotValidException exception) {
	//
	// String errorMsg = exception.getBindingResult().getFieldErrors().stream()
	// .map(DefaultMessageSourceResolvable::getDefaultMessage)
	// .findFirst()
	// .orElse(exception.getMessage());
	//
	// return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	// }

	private Object[] internationalizeArgs(Object[] args, Locale locale)
	{
		if (args == null || args.length == 0)
		{
			return null;
		}

		Collection<Object> i18nArgs = new ArrayList<>();

		Arrays.asList(args).stream().forEach(a -> {

			if (!I18nMessage.class.isAssignableFrom(a.getClass()))
			{
				i18nArgs.add(a);
				return;
			}

			I18nMessage msg = (I18nMessage) a;
			String i18nText = i18n.getMessage(msg, locale);
			if (i18nText == null)
			{
				i18nArgs.add(msg.getDefaultMessage());
				return;
			}
			i18nArgs.add(i18nText);
		});

		return i18nArgs.toArray();
	}
}