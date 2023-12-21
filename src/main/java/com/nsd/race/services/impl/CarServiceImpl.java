package com.nsd.race.services.impl;

import static com.nsd.race.mapper.CarMapper.toCar;
import static com.nsd.race.mapper.CarMapper.toCarDto;
import static com.nsd.race.mapper.CarMapper.toCarDtos;
import static com.nsd.race.mapper.CompanyMapper.toCompany;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsd.race.dto.CarDto;
import com.nsd.race.entities.Cars;
import com.nsd.race.exceptions.ResorcesNotFoundException;
import com.nsd.race.repositories.CarRepository;
import com.nsd.race.services.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	@Override
	public CarDto addCar(CarDto carDto) {
		Cars cars = toCar.apply(carDto)
				.orElseThrow(() -> new ResorcesNotFoundException("Car", "Converson Car to carDto"));
		cars.setCompany(toCompany.apply(carDto.getCompanyDto())
				.orElseThrow(() -> new ResorcesNotFoundException("compony", "Converson compony to componyDto")));
		return toCarDto.apply(carRepository.save(cars))
				.orElseThrow(() -> new ResorcesNotFoundException("Car", "Converson car to carDto"));
	}

	@Override
	public CarDto modifieCar(CarDto carDto, Integer carId) {
		Cars cars = carRepository.findById(carId)
				.orElseThrow(() -> new ResorcesNotFoundException("Car", "CarId", carId));
		cars.setCompany(toCompany.apply(carDto.getCompanyDto())
				.orElseThrow(() -> new ResorcesNotFoundException("company", "Converson companyDto to company")));
		cars.setModel(carDto.getModel());
		cars.setName(carDto.getName());
		cars.setPrice(carDto.getPrice());
		cars.setTopSpeed(carDto.getTopSpeed());
		return toCarDto.apply(carRepository.save(cars))
				.orElseThrow(() -> new ResorcesNotFoundException("Car", "Converson CarDto"));
	}

	@Override
	public List<CarDto> getAllCars() {
		return toCarDtos.apply(carRepository.findAll());
	}

	@Override
	public CarDto getCarById(Integer carId) {
		return toCarDto.apply(carRepository.findById(carId).get())
				.orElseThrow(() -> new ResorcesNotFoundException("Car", "Converson to car CarDto"));
	}

	@Override
	public void deleteCarById(Integer carId) {
		try {
			carRepository.deleteById(carId);
		} catch (Exception e) {
			throw new ResorcesNotFoundException("Car", "Car not found to remove.");
		}
	}

}
