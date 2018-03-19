package com.projetas.car.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@lombok.Setter
@lombok.Getter
@ConfigurationProperties("car-api")
public class CarApiProperty {

	private String originPermitida = "http://localhost:4200";

}
