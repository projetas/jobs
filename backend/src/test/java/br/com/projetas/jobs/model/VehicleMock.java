package br.com.projetas.jobs.model;

import java.math.BigDecimal;

public class VehicleMock {

    public static Vehicle mock(String brand, String model, String color, Integer year, BigDecimal price, boolean isNew) {
        return Vehicle.create(brand, model, color, year, price, isNew);
    }

    public static Vehicle ferrari250() {
        return mock("Ferrari", "250", "Red", 1962, BigDecimal.valueOf(350000), false);
    }

    public static Vehicle vwFusca() {
        return mock("Volkswagen", "Fusca", "White", 1970, BigDecimal.valueOf(10000), false);
    }
}
