package br.com.vehicle.support.exception;

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.vehicle.model.I18nMessage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Message
{
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;
	private Collection<MessageSubError> subErrors;

	@JsonIgnore
	private I18nMessage i18n;

	@JsonIgnore
	private Object[] args;

	public Message(final I18nMessage i18nInput, final Object[] argsInput)
	{
		timestamp = LocalDateTime.now();
		i18n = i18nInput;
		args = argsInput;
	}
	
	public Message(final String messageInput, final Object[] argsInput)
	{
		timestamp = LocalDateTime.now();
		message = messageInput;
		args = argsInput;
	}

	public static Message builder(final I18nMessage i18n, final Object... args)
	{
		return new Message(i18n, args);
	}
	
	public static Message builder(final String messageInput, final Object... args)
	{
		return new Message(messageInput, args);
	}
}
