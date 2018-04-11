package br.com.vehicle.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FetchRequest<T> extends Request
{
	private static final long serialVersionUID = 6700802282272165275L;

	public @NonNull T example;
	public @NonNull Integer startRow;
	public @NonNull Integer pageSize;
}
