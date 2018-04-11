package br.com.vehicle.model.domain;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.vehicle.model.AbstractModel;
import lombok.Builder;

@Builder
public class Vehicle extends AbstractModel
{
	@NotNull
	public VehicleModel model;

	@NotNull
	public VehicleColor color;

	@Positive
	@NotNull
	public Integer year;

	@Positive
	@NotNull
	public Double price;

	public String description;

	@NotNull
	public Boolean unused;

	@NotNull
	public LocalTime registerDate;

	public LocalTime modifiedDate;
}
