package com.nsd.race.services;

import java.util.List;

import com.nsd.race.dto.RaceDto;

public interface RaceService {

	RaceDto addRace(RaceDto raceDto);
	
	RaceDto updateRace(Integer raceId , RaceDto raceDto);
	
	List<RaceDto> getAllRaces();
	
	RaceDto getRaceById(Integer raceId);
	
	void cancelRace(Integer raceId);
}
