package com.nsd.race.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.nsd.race.dto.CarDto;

@Transactional(readOnly = false)
public interface CarService {
	
	CarDto addCar(CarDto carDto);
	
	CarDto modifieCar(CarDto carDto , Integer carId);
	
	List<CarDto> getAllCars();
	
	CarDto getCarById(Integer carId);
	
	void deleteCarById(Integer carId);
	
}
