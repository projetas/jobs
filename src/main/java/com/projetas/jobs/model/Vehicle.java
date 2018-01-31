package com.projetas.jobs.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Brand not empty, and less than 40 characters")
    @Length(max = 40)
    private String brand;
    @NotEmpty(message = "Model not empty, and less than 50 characters")
    @Length(max = 50)
    private String model;
    @NotEmpty(message = "Color not empty, and less than 30 characters")
    @Length(max = 30)
    private String color;
    @NotNull(message = "Year not empty")
    @Min(0)
    private int year;
    @NotNull(message = "Price not empty")
    @Min(0)
    private Double price;
    private String description;
    @NotNull(message = "New not empty")
    private boolean newModel;
    @NotNull(message = "Date Register not empty")
    private Date dateRegister;
    private Date dateUpdate;

    public Vehicle(){}

    public Vehicle(String brand, String model, String color, int year, Double price,
                   String description, boolean newModel, Date dateRegister, Date dateUpdate) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.description = description;
        this.newModel = newModel;
        this.dateRegister = dateRegister;
        this.dateUpdate = dateUpdate;
    }

    public Vehicle(Long id, String brand, String model, String color, int year, Double price,
                   String description, boolean newModel, Date dateRegister, Date dateUpdate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.description = description;
        this.newModel = newModel;
        this.dateRegister = dateRegister;
        this.dateUpdate = dateUpdate;
    }

    public Vehicle(int year, double price, String desc, boolean newModel, Date dateRegister, Date dateUpdate) {
        this.year = year;
        this.price = price;
        this.description = desc;
        this.newModel = newModel;
        this.dateRegister = dateRegister;
        this.dateUpdate = dateUpdate;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNewModel() {
        return newModel;
    }

    public void setNewModel(boolean newModel) {
        this.newModel = newModel;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", newModel=" + newModel +
                ", dateRegister=" + dateRegister +
                ", dateUpdate=" + dateUpdate +
                '}';
    }
}