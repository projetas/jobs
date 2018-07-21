package com.carservice.carServiceDocker.model;

import com.sun.istack.internal.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "car")
@EntityListeners(AuditingEntityListener.class)
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;

    @Column
    @NotNull
    private String brand;

    @Column
    @NotNull
    private String model;

    @Column
    @NotNull
    private String color;

    @Column
    @NotNull
    @Min(0)
    private int year;

    @Column
    @NotNull
    @Min(0)
    private double price;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    private boolean isNew;

    @Column
    @NotNull
    private LocalDate registerDate;

    @Column
    private LocalDate changeDate;



    public Long getIdCar() { return idCar; }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

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
