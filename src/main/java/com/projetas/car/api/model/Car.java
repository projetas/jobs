package com.projetas.car.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
@Entity
@Table(name = "car")
public class Car {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @NotNull
   @Size(max=40)
   private String brand;
   
   @NotNull
   @Size(max=50)
   private String model;

   @NotNull
   @Size(max=30)
   private String color;
   
   @NotNull
   @Size(min=4,max=4)
   private int year;
   
   private String description;

   @NotNull
   @Column(name = "new_car")
   private Boolean newCar;

   @NotNull
   @Column(name = "creation_date")
   private LocalDate creationDate;

   @Column(name = "update_date")
   private LocalDate updateDate;

   @NotNull
   private BigDecimal price;
   
   @JsonIgnore
   @Transient
   public boolean isNew() {
	   return !this.newCar;
   }
   	
}
