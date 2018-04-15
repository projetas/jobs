package br.com.vehicle.support.validation;

import static br.com.vehicle.support.exception.Message.builder;

import java.util.Collection;

import org.springframework.stereotype.Component;

import br.com.vehicle.model.I18nMessage;
import br.com.vehicle.support.exception.FailCode;
import br.com.vehicle.support.exception.VehicleException;

@Component
public final class Check
{
	public static <T> boolean isEmpty(final Collection<T> input, final Object... args) throws VehicleException
	{
		return isEmpty(input, FailCode.MSG_REQUIRED, args);
	}

	public static <T> boolean isEmpty(final Collection<T> input, final I18nMessage msg, final Object... args) throws VehicleException
	{
		if (isNull(input, msg) || input.isEmpty())
		{
			return true;
		}

		throw new VehicleException(builder(msg, args));
	}
	
	public static boolean isEmpty(final String input, final I18nMessage msg, final Object... args) throws VehicleException
	{
		if (isNull(input, msg) || input.isEmpty())
		{
			return true;
		}
		
		throw new VehicleException(builder(msg, args));
	}


	public static <T> boolean isNotEmpty(final Collection<T> input, final I18nMessage msg, final Object... args) throws VehicleException
	{
		if (isNull(input, msg) || input.isEmpty())
		{
			throw new VehicleException(builder(msg, args));
		}

		return true;
	}
	
	public static boolean isNotEmpty(final String input, final I18nMessage msg, final Object... args) throws VehicleException
	{
		if (isNotNull(input, msg) && !input.isEmpty())
		{
			return true;
		}
		
		throw new VehicleException(builder(msg, args));
	}

	public static <T> boolean isNotNull(final T input, final I18nMessage msg, final Object... args) throws VehicleException
	{
		if (input != null)
		{
			return true;
		}

		throw new VehicleException(builder(msg, args));
	}

	public static <T> boolean isNull(final T input, final Object... args) throws VehicleException
	{
		return isNull(input, FailCode.MSG_REQUIRED, args);
	}

	public static <T> boolean isNull(final T input, final I18nMessage msg, final Object... args) throws VehicleException
	{
		if (input == null)
		{
			return true;
		}

		throw new VehicleException(builder(msg, args));
	}

	public static <T> boolean isTrue(final boolean input, final I18nMessage msg, final Object... args) throws VehicleException
	{
		if (input)
		{
			return true;
		}

		throw new VehicleException(builder(msg, args));
	}

	public static <T> boolean isFalse(final boolean input, final I18nMessage msg, final Object... args) throws VehicleException
	{
		if (!input)
		{
			return true;
		}

		throw new VehicleException(builder(msg, args));
	}
}
