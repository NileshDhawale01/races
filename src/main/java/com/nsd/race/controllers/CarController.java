package com.nsd.race.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsd.race.dto.CarDto;
import com.nsd.race.services.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> getAllCars() {
		Map<Object, Object> map = new HashMap<>();
		map.put("data", carService.getAllCars());
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> addCar(@RequestBody CarDto carDto) {

		Map<Object, Object> map = new HashMap<>();
		map.put("data", carService.addCar(carDto));
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> updateCar(@RequestBody CarDto carDto, @PathVariable Integer id) {

		Map<Object, Object> map = new HashMap<>();
		map.put("data", carService.modifieCar(carDto, id));
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> getCarById(@PathVariable Integer id) {

		Map<Object, Object> map = new HashMap<>();
		map.put("data", carService.getCarById(id));
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> removeCarById(@PathVariable Integer id) {

		carService.deleteCarById(id);
		Map<Object, Object> map = new HashMap<>();
		map.put("data", "car removed.");
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
