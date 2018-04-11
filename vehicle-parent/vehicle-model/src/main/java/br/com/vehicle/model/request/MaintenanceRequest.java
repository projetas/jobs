package br.com.vehicle.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaintenanceRequest<T> extends Request
{
	private static final long serialVersionUID = -7698287740608474547L;

	public @NonNull T model;
}
