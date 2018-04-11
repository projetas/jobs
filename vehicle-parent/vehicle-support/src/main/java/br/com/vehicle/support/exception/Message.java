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
	public LocalDateTime timestamp;
	public String message;
	public String debugMessage;
	public Collection<MessageSubError> subErrors;

	@JsonIgnore
	public I18nMessage i18n;

	@JsonIgnore
	public Object[] args;

	public Message(final I18nMessage i18nInput, final Object[] argsInput)
	{
		timestamp = LocalDateTime.now();
		i18n = i18nInput;
		args = argsInput;
	}

	public static Message builder(final I18nMessage i18n, final Object... args)
	{
		return new Message(i18n, args);
	}
}
