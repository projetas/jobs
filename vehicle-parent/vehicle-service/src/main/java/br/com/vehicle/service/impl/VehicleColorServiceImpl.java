package br.com.vehicle.service.impl;

import static br.com.vehicle.support.exception.AppCode.VEHICLE_COLOR;
import static br.com.vehicle.support.exception.FailCode.MSG_ALREADY_EXIST;
import static br.com.vehicle.support.exception.FailCode.MSG_REQUIRED;
import static br.com.vehicle.support.handler.TextHandle.cleanText;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vehicle.model.domain.VehicleColor;
import br.com.vehicle.repository.VehicleColorRepository;
import br.com.vehicle.service.VehicleColorService;
import br.com.vehicle.support.validation.BeanValidator;
import br.com.vehicle.support.validation.Check;

@Service
@Transactional
public class VehicleColorServiceImpl implements VehicleColorService
{
	@Autowired
	private VehicleColorRepository colorRepository;

	@Autowired
	private BeanValidator validator;
	
	@Override
	public VehicleColor insert(String color)
	{
		VehicleColor vehicleColor = VehicleColor.builder().color(cleanText(color, true)).build();
		validator.validate(vehicleColor);
		Check.isFalse(colorRepository.findById(color).isPresent(), MSG_ALREADY_EXIST, VEHICLE_COLOR, color);
		return colorRepository.insert(vehicleColor);
	}

	@Override
	public void delete(String color)
	{
		VehicleColor vehicleColor = VehicleColor.builder().color(cleanText(color, true)).build();
		validator.validate(vehicleColor);
		colorRepository.deleteById(cleanText(color, true));
	}

	@Override
	public List<String> fetchAll()
	{
		Optional<Collection<VehicleColor>> colors = Optional.of(colorRepository.findAll());
		return colors.isPresent() ? colors.get().stream().map(VehicleColor::getColor).collect(Collectors.toList()) : null;
	}

	@Override
	public VehicleColor fetchOne(String color)
	{
		Check.isNotEmpty(color, MSG_REQUIRED, VEHICLE_COLOR);
		Optional<VehicleColor> one = colorRepository.findById(cleanText(color, true));
		return one.isPresent() ? one.get() : null;
	}
}
