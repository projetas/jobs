package br.com.projetas.vehiclerestwebapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="vehicle")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"register_timestamp", "update_timestamp"}, allowGetters = true)
public class Vehicle extends AbstractEntity {

	@NotNull(message = "Brand can not be null")
	@Size(max = 40, message = "Brand name length can not be greater than 40")
	@Column(name = "brand")
	private String brand;
	
	@NotNull(message = "Model can not be null")
	@Size(max = 50, message = "Model name length can not be greater than 50")
	@Column(name = "model")
	private String model;
	
	@NotNull(message = "Color can not be null")
	@Size(max = 30, message = "Color name length can not be greater than 30")
	@Column(name = "color")
	private String color;
	
	@NotNull(message = "Year can not be null")
	@Min(value = 0, message = "Year must be a positive number")
	@Column(name = "year")
	private int year;
	
	@NotNull(message = "Price can not be null")
	@Min(value = 0, message = "Price must be a positive value")
	@Column(name = "price", precision=10, scale=2)
	private double price;
	
	@Column(name = "description")
	private String description;
	
	@NotNull(message = "'Is new' can not be null")
	@Column(name = "is_new")
	private boolean isNew;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "register_timestamp", nullable = false, updatable = false)
	private Date registerTimestamp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "update_timestamp", nullable = false)
	private Date updateTimestamp;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String brand, String model, String color, int year, double price, 
					String description, boolean isNew) {
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.year = year;
		this.price = price;
		this.description = description;
		this.isNew = isNew;
	}

	public Vehicle(Long id, String brand, String model, String color, int year, double price, 
				String description, boolean isNew,
				Date registerTimestamp, Date updateTimestamp) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.year = year;
		this.price = price;
		this.description = description;
		this.isNew = isNew;
		this.registerTimestamp = registerTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public Date getRegisterTimestamp() {
		return registerTimestamp;
	}

	public void setRegisterTimestamp(Date registerTimestamp) {
		this.registerTimestamp = registerTimestamp;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	@Override
	public String toString() {
		return "Vehicle [brand=" + brand + ", model=" + model + ", color=" + color + ", year=" + year + ", price="
				+ price + ", description=" + description + ", isNew=" + isNew + ", registerTimestamp="
				+ registerTimestamp + ", updateTimestamp=" + updateTimestamp + "]";
	}
	
}
