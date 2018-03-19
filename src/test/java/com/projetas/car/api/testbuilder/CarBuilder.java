package com.projetas.car.api.testbuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.projetas.car.api.model.Car;

public class CarBuilder {
	
   private Long cod;
   private String brand;  
   private String model;
   private String color;
   private Integer year;
   private String description;
   private Boolean newCar;
   private LocalDate creationDate;
   private LocalDate updateDate;
   private BigDecimal price;

   public CarBuilder withCod(Long cod)
   {
	   this.cod = cod;
	   return this;
   }
   
   public CarBuilder withBrand(String brand)
   {
	   this.brand = brand;
	   return this;
   }
   
   public CarBuilder withModel(String model)
   {
	   this.model = model;
	   return this;
   }
   
   public CarBuilder withColor(String color)
   {
	   this.color = color;
	   return this;
   }
   
   public CarBuilder withYear(Integer year)
   {
	   this.year = year;
	   return this;
   }
   
   public CarBuilder withDescription(String description)
   {
	   this.description = description;
	   return this;
   }
   
   public CarBuilder withCreationDate(LocalDate creationDate)
   {
	   this.creationDate = creationDate;
	   return this;
   }
   
   public CarBuilder withUpdateDate(LocalDate updateDate)
   {
	   this.updateDate = updateDate;
	   return this;
   }
   
   public CarBuilder withNewCar(Boolean newCar)
   {
	   this.newCar = newCar;
	   return this;
   }
   
   public CarBuilder withPrice(BigDecimal price)
   {
	   this.price = price;
	   return this;
   }
   
   public Car build()
   {
	   return new Car(cod,brand,model,color,year,description,newCar,creationDate,updateDate,price);
   }
}
