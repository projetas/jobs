package br.com.vehicle.model.response;

import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings("unused")
public class Response<T> implements Serializable
{
	private static final long serialVersionUID = 9147771433269650132L;

	public Collection<T> results;
}
