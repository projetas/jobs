package br.com.projetos.vehicle.expections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "There are objects that exceed the maximum size.")
public class LengthException extends RuntimeException {

	private static final long serialVersionUID = -4974076631566790402L;

	public LengthException() {
		super("There are objects that exceed the maximum size.");
	}
}
