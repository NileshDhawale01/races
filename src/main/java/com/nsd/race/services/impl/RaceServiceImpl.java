package com.nsd.race.services.impl;

import static com.nsd.race.mapper.CarMapper.toCar;
import static com.nsd.race.mapper.CatagoryMapper.toCatagory;
import static com.nsd.race.mapper.CompanyMapper.toCompany;
import static com.nsd.race.mapper.RaceMapper.toRace;
import static com.nsd.race.mapper.RaceMapper.toRaceDto;
import static com.nsd.race.mapper.RaceMapper.toRaceDtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsd.race.dto.RaceDto;
import com.nsd.race.entities.Race;
import com.nsd.race.repositories.RaceRepo;
import com.nsd.race.services.RaceService;

@Service
public class RaceServiceImpl implements RaceService {

	@Autowired
	private RaceRepo raceRepo;

	@Override
	public RaceDto addRace(RaceDto raceDto) {
		return toRaceDto.apply(raceRepo.save(toRace.apply(raceDto).get())).get();
	}

	@Override
	public RaceDto updateRace(Integer raceId, RaceDto raceDto) {
		
		Race race = raceRepo.findById(raceId).get();
		race.setCars(toCar.apply(raceDto.getCarsDto()).get());
		race.setCatagory(toCatagory.apply(raceDto.getCatagoryDto()).get());

		race.setCountry(raceDto.getCountry());
		race.setDate(raceDto.getDate());
		race.setTrack(raceDto.getTrack());
		race.setDescription(raceDto.getDescription());
		return toRaceDto.apply(raceRepo.save(race)).get();
	}

	@Override
	public List<RaceDto> getAllRaces() {
		return toRaceDtos.apply(raceRepo.findAll());
	}

	@Override
	public RaceDto getRaceById(Integer raceId) {
		return toRaceDto.apply(raceRepo.findById(raceId).get()).get();
	}

	@Override
	public void cancelRace(Integer raceId) {
		
		Race race = raceRepo.findById(raceId).get();
		raceRepo.delete(race);
	}

}
