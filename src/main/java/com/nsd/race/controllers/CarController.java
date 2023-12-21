package com.nsd.race.controllers;

import static com.nsd.race.contants.ApiContants.CAR;
import static com.nsd.race.contants.ApiContants.CAR_ID;
import static com.nsd.race.contants.NameContants.ADMIN_ATHORITY;
import static com.nsd.race.contants.NameContants.DATA;
import static com.nsd.race.contants.NameContants.SUCCESS;

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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CAR)
@Slf4j
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> getAllCars() {
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, carService.getAllCars());
		map.put(SUCCESS, true);
		log.info("returning from getAllCars()");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> addCar(@RequestBody CarDto carDto) {

		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, carService.addCar(carDto));
		map.put(SUCCESS, true);
		log.info("returning from addCar()");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@PutMapping(CAR_ID)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> updateCar(@RequestBody CarDto carDto, @PathVariable Integer id) {

		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, carService.modifieCar(carDto, id));
		map.put(SUCCESS, true);
		log.info("returning from updateCar()");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping(CAR_ID)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> getCarById(@PathVariable Integer id) {

		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, carService.getCarById(id));
		map.put(SUCCESS, true);
		log.info("returning from getCarById()");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping(CAR_ID)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> removeCarById(@PathVariable Integer id) {

		carService.deleteCarById(id);
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, "car removed.");
		map.put(SUCCESS, true);
		log.info("returning from removeCarById()");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
