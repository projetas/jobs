package br.com.diego.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.model.Vehicle;
import br.com.diego.repository.VehicleRepository;

@RestController
@RequestMapping(path = "/vehicle")
public class VehicleResource {

	@Autowired
	private VehicleRepository repository;
	
	@GetMapping("/find")
	public List<Vehicle> find() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> findOne(@PathVariable Long id) {
		return ResponseEntity.ok(repository.findOne(id));
	}
	
	@PostMapping("/find")
	public ResponseEntity<List<Vehicle>> find(@RequestBody Map<String, Object> param){
		
		return ResponseEntity.ok(repository.findByParam(param.get("model").toString()));
	}
	
	@PostMapping
	public ResponseEntity<Vehicle> save(@RequestBody Vehicle vehicle) {
		
		if(vehicle.getId() != null) {
			vehicle.setUpdate(new Date());
		}else {
			vehicle.setRegister(new Date());
		}
		
		return ResponseEntity.ok(repository.save(vehicle));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
		repository.delete(id);
		Map<String, String> returns = new HashMap<String, String>();
		returns.put("mensagens", "sucess.");
		return ResponseEntity.ok(returns);
	}	
}
