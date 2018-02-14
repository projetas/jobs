package br.com.diego.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 8850188005510368334L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_vehicle")
	@SequenceGenerator(name = "sequence_vehicle", sequenceName = "vehicle_id_seq", allocationSize = 1)
	private Long id;
	
	private String brand;
	
	private String model;
	
	private Long year;
	
	private BigDecimal price;
	
	private String description;
	
	@Column(name = "isnew")
	private Boolean isNew;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date register;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date update;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getRegister() {
		return register;
	}

	public void setRegister(Date register) {
		this.register = register;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}
}
