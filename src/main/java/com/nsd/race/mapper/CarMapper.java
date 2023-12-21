package com.nsd.race.mapper;

import static com.nsd.race.util.FunctionUtil.evalMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.nsd.race.dto.CarDto;
import com.nsd.race.entities.Cars;

public class CarMapper {

	private CarMapper() {}
	
	public static final Function<Cars, Optional<CarDto>> toCarDto = e->{
		Optional<CarDto> carDto = evalMapper(e, CarDto.class);
		return carDto;
	};
	
	public static final Function<CarDto, Optional<Cars>> toCar = e->{
		Optional<Cars> cars = evalMapper(e, Cars.class);
//		cars.ifPresent(c->{
//			c.setCompany(toCompany.apply(e.getCompanyDto()).get());
//		});
		return cars;
	};
	
	public static final Function<Collection<Cars>, List<CarDto>> toCarDtos = e-> {
		return e.stream().map(c->toCarDto.apply(c).get()).collect(Collectors.toList());
	};
	
	public static final Function<Collection<CarDto>, List<Cars>> toCars = e->{
		return e.stream().map(c->toCar.apply(c).get()).collect(Collectors.toList());
	};
}
