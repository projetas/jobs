package br.com.rest.veiculo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Veiculo")
@EntityListeners(AuditingEntityListener.class)
public class Veiculo extends AbstractEntity {

	@NotNull(message = "Required field brand")
	@Column(name = "brand")
	private String brand;

	@NotNull(message = "Required field model")
	@Column(name = "model")
	private String model;

	@NotNull(message = "Required field color")
	@Column(name = "color")
	private String color;

	@NotNull(message = "Required field Year")
	@Min(value = 0, message = "invalid Year")
	@Column(name = "year")
	private int year;

	@NotNull(message = "Required field Price")
	@Column(name = "price", precision=10, scale=2)
	private double price;

	@Column(name = "description")
	private String description;

	@NotNull(message = "Required field Price")
	@Column(name = "is_new")
	private boolean isNew;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "create_at", nullable = false, updatable = false)
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "update_at", nullable = false)
	private Date updateAt;

	public Veiculo() {

	}

	public Veiculo(String brand, String model, String color, int year, double price,
					String description, boolean isNew) {
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.year = year;
		this.price = price;
		this.description = description;
		this.isNew = isNew;
	}

	public Veiculo(Long id, String brand, String model, String color, int year, double price,
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
		this.createAt = registerTimestamp;
		this.updateAt = updateTimestamp;
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

	/**
	 * @return the creatAt
	 */
	public Date getCreateAt() {
		return createAt;
	}

	/**
	 * @param creatAt the creatAt to set
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * @return the updateAt
	 */
	public Date getUpdateAt() {
		return updateAt;
	}

	/**
	 * @param updateAt the updateAt to set
	 */
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Veiculo [brand=" + brand + ", model=" + model + ", color=" + color + ", year=" + year + ", price=" + price
				+ ", description=" + description + ", isNew=" + isNew + ", creatAt=" + createAt + ", updateAt=" + updateAt + "]";
	}
}
