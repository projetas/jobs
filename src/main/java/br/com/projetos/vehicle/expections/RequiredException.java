package br.com.projetos.vehicle.expections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "There are null required objects.")
public class RequiredException extends RuntimeException {

	private static final long serialVersionUID = -8590659723449458288L;

	public RequiredException() {
		super("There are objects that exceed the maximum size.");
	}
}
