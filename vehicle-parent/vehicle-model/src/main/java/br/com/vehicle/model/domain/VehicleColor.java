package br.com.vehicle.model.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class VehicleColor
{
	@Id
	@NotNull
	@Max(value = 30)
	private String color;
}
