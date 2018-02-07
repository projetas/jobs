package br.com.projetos.vehicle.model;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
	
	private static final long serialVersionUID = -2801608360990492939L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "vehicle_generator")
	@SequenceGenerator(allocationSize = 1, name = "vehicle_generator", initialValue = 1)
	@Column(name = "id_vehicle")
	private Long id;
	
	@Column(name = "description", columnDefinition = "text")
	private String description;

	@Column(name = "brand", nullable = false, length = 40)
	private String brand;

	@Column(name = "model", nullable = false, length = 50)
	private String model;

	@Column(name = "color", nullable = false, length = 30)
	private String color;

	@Column(name = "year", nullable = false)
	private Long year;

	@Column(name = "price", nullable = false, scale = 2)
	private BigDecimal price;

	@Column(name = "new_car", nullable = false)
	private Boolean newCar;

	@Column(name = "register", nullable = false, updatable = false)
	@DateTimeFormat(pattern="yyyy-MM-yyyy hh:mm:ss.SSS a")
	private Date registrationDate;

	@Column(name = "update")
	@DateTimeFormat(pattern="yyyy-MM-yyyy hh:mm:ss.SSS a")
	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getNewCar() {
		return newCar;
	}

	public void setNewCar(Boolean newCar) {
		this.newCar = newCar;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@Transient
	public String getNew(){
		return newCar ? "Yes" : "No";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((newCar == null) ? 0 : newCar.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (newCar == null) {
			if (other.newCar != null)
				return false;
		} else if (!newCar.equals(other.newCar))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
}
