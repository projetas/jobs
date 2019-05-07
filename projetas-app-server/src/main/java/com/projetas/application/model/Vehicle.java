package com.projetas.application.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = -6418670117191374363L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 40)
	@Column(length = 40, nullable = false)
	private String brand;

	@NotBlank
	@Size(max = 50)
	@Column(length = 50, nullable = false)
	private String model;

	@NotBlank
	@Size(max = 30)
	@Column(length = 30, nullable = false)
	private String color;

	@NotNull
	@Positive
	@Column(nullable = false)
	private Integer year;

	@NotNull
	@Positive
	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = true)
	private String description;

	@NotNull
	@Column(nullable = false)
	private Boolean isNew;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date saveDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date updateDate;

	@PrePersist
	public void prePersist() {
		this.saveDate = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.updateDate = new Date();
	}

	public Vehicle() {
	}

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

	public Date getSaveDate() {
		return saveDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

}
