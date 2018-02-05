package br.com.projetos.vehicle.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.projetos.vehicle.expections.LengthException;
import br.com.projetos.vehicle.expections.RequiredException;
import br.com.projetos.vehicle.model.Vehicle;
import br.com.projetos.vehicle.repository.VehicleRepository;

@Service
public class VehicleService implements Serializable {

	private static final long serialVersionUID = -8050544274826741632L;

	@Autowired
	private VehicleRepository vericleRepository;

	public Vehicle saveAndFlush(Vehicle vehicle) {
		if(vehicle.getId() == null){
			vehicle.setRegistrationDate(new Date());
		} else {
			vehicle.setUpdateDate(new Date());
		}
		
		if(vehicle.getPrice().compareTo(BigDecimal.ZERO) <= 0){
			throw new RequiredException();
		}
		
		validateFields(vehicle);
		
		return vericleRepository.saveAndFlush(vehicle);
	}

	public void deleteById(Long id) {
		vericleRepository.deleteById(id);
	}
	
	public Vehicle findById(Long id) {
		return vericleRepository.getOne(id);
	}

	public List<Vehicle> findAll() {
		return vericleRepository.findAll();
	}
	
	public void validateFields(Vehicle vehicle){
		try {
			for (Field field : Vehicle.class.getDeclaredFields()) {
				field.setAccessible(true);
				Column column = field.getAnnotation(Column.class);
				
				if(column != null){
					Object object = field.get(vehicle);
					if(column.nullable() == false){
						if(StringUtils.isEmpty(object)){
							throw new RequiredException();
						}
					}
					
					if(!column.columnDefinition().equals("text") && object != null && object.toString().length() > column.length()){
						throw new LengthException();
					}
				}
			}
			
		} catch (SecurityException | IllegalAccessException e) {
			throw new RuntimeException("Houve um erro na validação dos campos.");
		}
	}
}
