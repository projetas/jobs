package br.com.vehicle.support.exception;

import java.util.ArrayList;
import java.util.Collection;

public class VehicleException extends RuntimeException
{
	private static final long serialVersionUID = -1820514792985663925L;

	private final Collection<Message> messages = new ArrayList<>();

	public VehicleException(final Message... msgs)
	{

		for (final Message m : msgs)
		{
			messages.add(m);
		}
	}

	public void addMessages(final Collection<Message> msgs)
	{

		if (msgs == null || msgs.isEmpty())
		{
			return;
		}

		messages.addAll(msgs);
	}

	public Collection<Message> getMessages()
	{
		return messages;
	}
}
