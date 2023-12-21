package com.nsd.race.services.impl;

import static com.nsd.race.mapper.CarMapper.*;
import static com.nsd.race.mapper.CompanyMapper.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsd.race.dto.CarDto;
import com.nsd.race.entities.Cars;
import com.nsd.race.repositories.CarRepository;
import com.nsd.race.services.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	@Override
	public CarDto addCar(CarDto carDto) {
		Cars cars = toCar.apply(carDto).get();
		cars.setCompany(toCompany.apply(carDto.getCompanyDto()).get());
		return toCarDto.apply(carRepository.save(cars)).get();
	}

	@Override
	public CarDto modifieCar(CarDto carDto, Integer carId) {
		Cars cars = carRepository.findById(carId).get();
		cars.setCompany(toCompany.apply(carDto.getCompanyDto()).get());
		cars.setModel(carDto.getModel());
		cars.setName(carDto.getName());
		cars.setPrice(carDto.getPrice());
		cars.setTopSpeed(carDto.getTopSpeed());
		return toCarDto.apply(carRepository.save(cars)).get();
	}

	@Override
	public List<CarDto> getAllCars() {
		return toCarDtos.apply(carRepository.findAll());
	}

	@Override
	public CarDto getCarById(Integer carId) {
		return toCarDto.apply(carRepository.findById(carId).get()).get();
	}

	@Override
	public void deleteCarById(Integer carId) {
//		Cars cars = carRepository.findById(carId).get();
//		carRepository.delete(cars);

		carRepository.deleteById(carId);
	}

}
