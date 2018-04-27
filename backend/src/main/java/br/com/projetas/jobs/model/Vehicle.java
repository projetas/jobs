package br.com.projetas.jobs.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "VEHICLE")
public class Vehicle {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String brand;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(nullable = false, length = 50)
    private String color;

    @Column(nullable = false, length = 4)
    private int year;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    @Column(name = "new", nullable = false)
    private boolean isNew;

    @Column(nullable = false)
    private LocalDateTime creation;

    private LocalDateTime update;

    public Vehicle() {
        creation = LocalDateTime.now();
    }

    public Vehicle(String brand, String model, String color, Integer year, BigDecimal price, boolean isNew) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.isNew = isNew;
        creation = LocalDateTime.now();
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

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public LocalDateTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalDateTime update) {
        this.update = update;
    }

    static Vehicle create(String brand, String model, String color, Integer year, BigDecimal price, boolean isNew) {
        return new Vehicle(brand, model, color, year, price, isNew);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return year == vehicle.year &&
                Objects.equals(id, vehicle.id) &&
                Objects.equals(brand, vehicle.brand) &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, brand, model, color, year, creation);
    }
}
