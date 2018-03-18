package com.projetas.car.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@lombok.EqualsAndHashCode
@lombok.Setter
@lombok.Getter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long cod;
   
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
   @Min(1900)
   @Max(2018)
   private Integer year;
   
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
   @DecimalMin("0.00")
   private BigDecimal price;
   
   @JsonIgnore
   @Transient
   public boolean isNew() {
	   return !this.newCar;
   }
  }
