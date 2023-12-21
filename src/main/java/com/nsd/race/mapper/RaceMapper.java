package com.nsd.race.mapper;

import static com.nsd.race.mapper.CarMapper.toCar;
import static com.nsd.race.mapper.CarMapper.toCarDto;
import static com.nsd.race.mapper.CatagoryMapper.toCatagory;
import static com.nsd.race.mapper.CatagoryMapper.toCatagoryDto;
import static com.nsd.race.mapper.CompanyMapper.toCompany;
import static com.nsd.race.mapper.CompanyMapper.toCompanyDto;
import static com.nsd.race.util.FunctionUtil.evalMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.nsd.race.dto.RaceDto;
import com.nsd.race.entities.Race;

public class RaceMapper {

	private RaceMapper() {
	}

	public static final Function<Race, Optional<RaceDto>> toRaceDto = e -> {

		Optional<RaceDto> raceDto = evalMapper(e, RaceDto.class);
		raceDto.ifPresent(p -> {
			p.setCarsDto(toCarDto.apply(e.getCars()).get());
			p.setCatagoryDto(toCatagoryDto.apply(e.getCatagory()).get());
		});
		return raceDto;
	};

	public static final Function<RaceDto, Optional<Race>> toRace = e -> {

		Optional<Race> race = evalMapper(e, Race.class);
		race.ifPresent(p -> {
			p.setCars(toCar.apply(e.getCarsDto()).get());
			p.setCatagory(toCatagory.apply(e.getCatagoryDto()).get());
			//p.setCompany(toCompany.apply(e.getCompanyDto()).get());
		});
		return race;
	};
	
	public static final Function<Collection<Race>, List<RaceDto>> toRaceDtos = e->{
		return e.stream().map(p->toRaceDto.apply(p).get()).collect(Collectors.toList());
	};
	
	public static final Function<Collection<RaceDto>, List<Race>> toRaces = e->{
		return e.stream().map(p->toRace.apply(p).get()).collect(Collectors.toList());
	};
}
