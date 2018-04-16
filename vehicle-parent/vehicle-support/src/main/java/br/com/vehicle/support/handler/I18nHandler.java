package br.com.vehicle.support.handler;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import br.com.vehicle.model.I18nMessage;

@Component
public class I18nHandler
{
	@Autowired
	private MessageSource messageSource;

	public String getMessage(I18nMessage appCode, Locale locale, Object... args)
	{
		return messageSource.getMessage(appCode.getCode(), args, locale);
	}
	
	public String getMessage(String code, Locale locale, Object... args)
	{
		return messageSource.getMessage(code, args, locale);
	}

	public String getMessage(I18nMessage appCode, Locale locale)
	{
		return messageSource.getMessage(appCode.getCode(), null, locale);
	}
	
	public String getMessage(String code, Locale locale)
	{
		return messageSource.getMessage(code, null, locale);
	}
}
