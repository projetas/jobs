package br.com.vehicle.api.rest;

import static br.com.vehicle.support.exception.AppCode.MSG_DELETE_SUCCESS;
import static br.com.vehicle.support.exception.AppCode.MSG_INSERT_SUCCESS;
import static br.com.vehicle.support.exception.AppCode.MSG_UPDATE_SUCCESS;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import br.com.vehicle.model.domain.Vehicle;
import br.com.vehicle.model.domain.VehicleType;
import br.com.vehicle.model.request.FetchRequest;
import br.com.vehicle.model.response.FetchResponse;
import br.com.vehicle.model.response.Response;
import br.com.vehicle.service.VehicleService;
import br.com.vehicle.support.handler.I18nHandler;

@RestController
@RequestMapping("/vehicle/api")
public class VehicleRestApi
{
	@Autowired
	private VehicleService vehicleVehicleService;

	@Autowired
	private I18nHandler i18n;

	@PostMapping("/insert")
	@ResponseBody
	public ResponseEntity<Response> insert(@RequestBody Vehicle vehicle, WebRequest request)
	{
		vehicleVehicleService.insert(vehicle);
		String message = i18n.getMessage(MSG_INSERT_SUCCESS, request.getLocale(), vehicle.getModel());
		return new ResponseEntity<Response>(Response.builder().message(message).build(), CREATED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<Response> update(@RequestBody Vehicle vehicle, WebRequest request)
	{
		vehicleVehicleService.update(vehicle);
		String message = i18n.getMessage(MSG_UPDATE_SUCCESS, request.getLocale(), vehicle.getModel());
		return new ResponseEntity<Response>(Response.builder().message(message).build(), OK);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseEntity<Response> delete(@RequestParam("model") String model, WebRequest request)
	{
		vehicleVehicleService.delete(model);
		String message = i18n.getMessage(MSG_DELETE_SUCCESS, request.getLocale(), model);
		return new ResponseEntity<Response>(Response.builder().message(message).build(), OK);
	}

	@GetMapping("/fetch")
	@ResponseBody
	public ResponseEntity<FetchResponse<Vehicle>> fetchAllVehicles(@RequestBody FetchRequest<Vehicle> request, WebRequest webRequest)
	{
		return new ResponseEntity<FetchResponse<Vehicle>>(vehicleVehicleService.fetchAll(request), OK);
	}

	@GetMapping("/fetch/model/{model}")
	@ResponseBody
	public ResponseEntity<Vehicle> fetchAllByModel(@PathVariable("model") String model, WebRequest webRequest)
	{
		return new ResponseEntity<Vehicle>(vehicleVehicleService.fetchOne(model), OK);
	}

	@GetMapping("/fetch/brand/{brand}/{page}/{size}")
	@ResponseBody
	public ResponseEntity<FetchResponse<Vehicle>> fetchAllByBrand(@PathVariable("brand") String brand, @PathVariable("page") Integer page, @PathVariable("size") Integer size,
			WebRequest webRequest)
	{
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(brand);
		FetchRequest<Vehicle> request = new FetchRequest<Vehicle>();
		request.setExample(vehicle);
		request.setPage(page);
		request.setSize(size);
		return fetchAllVehicles(request, webRequest);
	}

	@GetMapping("/fetch/type/{type}/{page}/{size}")
	@ResponseBody
	public ResponseEntity<FetchResponse<Vehicle>> fetchAllByType(@PathVariable("type") VehicleType type, @PathVariable("page") Integer page, @PathVariable("size") Integer size,
			WebRequest webRequest)
	{
		Vehicle vehicle = new Vehicle();
		vehicle.setType(type);
		FetchRequest<Vehicle> request = new FetchRequest<Vehicle>();
		request.setExample(vehicle);
		request.setPage(page);
		request.setSize(size);
		return fetchAllVehicles(request, webRequest);
	}

	@GetMapping("/fetch/color/{color}/{page}/{size}")
	@ResponseBody
	public ResponseEntity<FetchResponse<Vehicle>> fetchAllByColor(@PathVariable("color") String color, @PathVariable("page") Integer page, @PathVariable("size") Integer size,
			WebRequest webRequest)
	{
		Vehicle vehicle = new Vehicle();
		vehicle.setColor(color);
		FetchRequest<Vehicle> request = new FetchRequest<Vehicle>();
		request.setExample(vehicle);
		request.setPage(page);
		request.setSize(size);
		return fetchAllVehicles(request, webRequest);
	}
}
