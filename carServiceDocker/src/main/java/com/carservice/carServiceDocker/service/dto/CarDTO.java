package com.carservice.carServiceDocker.service.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CarDTO {

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private String color;

    @NotNull
    @Min(value = 0)
    private int year;

    @NotNull
    @Min(value = 0)
    private double price;

    @NotNull
    private String description;

    @NotNull
    private boolean isNew;

    @NotNull
    private LocalDate registerDate;

    @NotNull
    private LocalDate changeDate;

    public String getBrand() { return brand; }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() { return model; }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() { return color; }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() { return year; }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsNew() { return isNew; }

    public void setIsNew(boolean isNew) {
        isNew = isNew;
    }

    public LocalDate getRegisterDate() { return registerDate; }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDate getChangeDate() { return changeDate; }

    public void setChangeDate(LocalDate updateDate) { this.changeDate = updateDate; }
}
