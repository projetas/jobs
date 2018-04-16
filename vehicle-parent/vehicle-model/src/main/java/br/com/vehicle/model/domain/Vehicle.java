package br.com.vehicle.model.domain;

import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Vehicle
{
	public Vehicle(String model)
	{
		setModel(model);
	}

	@Id
	@NotBlank
	@Size(max = 50)
	private String model;
	
	@NotNull
	private VehicleBrand brand;

	@NotNull
	private VehicleType type;

	@NotNull
	private VehicleColor color;

	@Positive
	@NotNull
	private Integer year;

	@Positive
	@NotNull
	private Double price;

	private String description;

	@NotNull
	private Boolean unused;

	private LocalTime registerDate;

	private LocalTime modifiedDate;

	public String getBrand()
	{
		return brand != null ? brand.getBrand() : null;
	}

	public void setBrand(String brand)
	{
		this.brand = VehicleBrand.builder().brand(brand).build();
	}

	public void setBrand(VehicleBrand brand)
	{
		this.brand = brand;
	}

	public String getColor()
	{
		return color != null ? color.getColor() : null;
	}

	public void setColor(String color)
	{
		this.color = VehicleColor.builder().color(color).build();
	}

	public void setColor(VehicleColor color)
	{
		this.color = color;
	}
}
