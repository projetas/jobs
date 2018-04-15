package br.com.vehicle.api.rest;

import static br.com.vehicle.support.exception.AppCode.MSG_DELETE_SUCCESS;
import static br.com.vehicle.support.exception.AppCode.MSG_INSERT_SUCCESS;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import br.com.vehicle.model.response.FetchResponse;
import br.com.vehicle.model.response.Response;
import br.com.vehicle.service.VehicleColorService;
import br.com.vehicle.support.handler.I18nHandler;

@RestController
@RequestMapping("vehicle/color/api")
public class VehicleColorRestApi
{
	@Autowired
	private I18nHandler i18n;

	@Autowired
	private VehicleColorService colorService;

	@PostMapping(value = "/insert", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Response> insert(@RequestParam("color") String color, WebRequest request)
	{
		colorService.insert(color);
		String message = i18n.getMessage(MSG_INSERT_SUCCESS, request.getLocale(), color);
		return new ResponseEntity<Response>(Response.builder().message(message).statusCode(CREATED.value()).build(), CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseEntity<Response> delete(@RequestParam("color") String color, WebRequest request)
	{
		colorService.delete(color);
		String message = i18n.getMessage(MSG_DELETE_SUCCESS, request.getLocale(), color);
		return new ResponseEntity<Response>(Response.builder().message(message).statusCode(OK.value()).build(), OK);
	}

	@GetMapping("/fetch")
	@ResponseBody
	public ResponseEntity<FetchResponse<String>> fetchAllColors(WebRequest request)
	{
		FetchResponse<String> response = FetchResponse.<String>builder().results(colorService.fetchAll()).build();
		return new ResponseEntity<FetchResponse<String>>(response, OK);
	}
}
