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

	private @NonNull T example;
	private @NonNull Integer page;
	private @NonNull Integer size;
}
