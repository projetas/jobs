package br.com.vehicle.model.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import br.com.vehicle.model.AbstractModel;

public class VehicleColor extends AbstractModel
{
	@NotNull
	@Max(value = 30)
	public String color;
}
