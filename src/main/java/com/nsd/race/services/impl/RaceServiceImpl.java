package com.nsd.race.services.impl;

import static com.nsd.race.mapper.CarMapper.toCar;
import static com.nsd.race.mapper.CatagoryMapper.toCatagory;
import static com.nsd.race.mapper.RaceMapper.toRace;
import static com.nsd.race.mapper.RaceMapper.toRaceDto;
import static com.nsd.race.mapper.RaceMapper.toRaceDtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsd.race.dto.RaceDto;
import com.nsd.race.entities.Race;
import com.nsd.race.exceptions.ResorcesNotFoundException;
import com.nsd.race.repositories.RaceRepo;
import com.nsd.race.services.RaceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RaceServiceImpl implements RaceService {

	@Autowired
	private RaceRepo raceRepo;

	@Override
	public RaceDto addRace(RaceDto raceDto) {
		return toRaceDto.apply(raceRepo.save(toRace.apply(raceDto).get())).orElseThrow(()-> new ResorcesNotFoundException("Race", "race converson error"));
	}

	@Override
	public RaceDto updateRace(Integer raceId, RaceDto raceDto) {
		
		Race race = raceRepo.findById(raceId).orElseThrow(()-> new ResorcesNotFoundException("Race", "raceID",raceId));
		race.setCars(toCar.apply(raceDto.getCarsDto()).orElseThrow(()-> new ResorcesNotFoundException("Car", "car converson error")));
		race.setCatagory(toCatagory.apply(raceDto.getCatagoryDto()).orElseThrow(()-> new ResorcesNotFoundException("catagory", "catagory converson error")));

		race.setCountry(raceDto.getCountry());
		race.setDate(raceDto.getDate());
		race.setTrack(raceDto.getTrack());
		race.setDescription(raceDto.getDescription());
		log.info("returning from race sevice method updateRace()");
		return toRaceDto.apply(raceRepo.save(race)).orElseThrow(()-> new ResorcesNotFoundException("Race", "race converson error"));
	}

	@Override
	public List<RaceDto> getAllRaces() {
		return toRaceDtos.apply(raceRepo.findAll());
	}

	@Override
	public RaceDto getRaceById(Integer raceId) {
		return toRaceDto.apply(raceRepo.findById(raceId).get()).orElseThrow(()-> new ResorcesNotFoundException("Race", "raceId",raceId));
	}

	@Override
	public void cancelRace(Integer raceId) {
		
		Race race = raceRepo.findById(raceId).orElseThrow(()-> new ResorcesNotFoundException("Race", "raceId",raceId));
		raceRepo.delete(race);
	}

}
