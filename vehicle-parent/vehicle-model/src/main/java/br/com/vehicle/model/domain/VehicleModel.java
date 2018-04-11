package br.com.vehicle.model.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import br.com.vehicle.model.AbstractModel;

public class VehicleModel extends AbstractModel
{
	@NotNull
	public VehicleBrand brand;

	@NotNull
	@Max(value = 50)
	public String modelName;
}
