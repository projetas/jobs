package br.com.rest.veiculo.utils;

import javax.validation.ValidationException;

import br.com.rest.veiculo.entity.Veiculo;


public class Util {
	public static boolean validar(Veiculo veiculo) throws ValidationException {

		if(veiculo.getBrand() == null) {
			throw new ValidationException();
		}

		if(veiculo.getColor() == null) {
			throw new ValidationException();
		}

		if(veiculo.getModel() == null) {
			throw new ValidationException();
		}

		if(veiculo.getPrice() == 0) {
			throw new ValidationException();
		}

		return true;
	}
}
