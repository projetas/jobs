package com.projetas.application.message.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.projetas.application.model.Vehicle;

public class VehicleForm {

	@NotBlank
	@Size(max = 40)
	private String brand;

	@NotBlank
	@Size(max = 50)
	private String model;

	@NotBlank
	@Size(max = 30)
	private String color;

	@NotNull
	@Positive
	private Integer year;

	@NotNull
	@Positive
	private BigDecimal price;

	private String description;

	@NotNull
	private Boolean isNew;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Vehicle get() {
		return this.to(new Vehicle());
	}

	public Vehicle to(Vehicle vehicle) {
		vehicle.setBrand(this.brand);
		vehicle.setModel(this.model);
		vehicle.setColor(this.color);
		vehicle.setYear(this.year);
		vehicle.setPrice(this.price);
		vehicle.setDescription(this.description);
		vehicle.setIsNew(this.isNew);
		return vehicle;
	}

}
