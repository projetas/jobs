package br.com.vehicle.model.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import br.com.vehicle.model.AbstractModel;

public class VehicleBrand extends AbstractModel
{
	@NotNull
	@Max(value = 40)
	public String name;
}
