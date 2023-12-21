package com.nsd.race.controllers;

import static com.nsd.race.contants.ApiContants.ADD_RACE;
import static com.nsd.race.contants.ApiContants.RACE;
import static com.nsd.race.contants.ApiContants.RACE_ID;
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

import com.nsd.race.dto.RaceDto;
import com.nsd.race.services.RaceService;

@RestController
@RequestMapping(RACE)
public class RaceController {

	@Autowired
	private RaceService raceService;

	@PostMapping(ADD_RACE)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> addRace(@RequestBody RaceDto raceDto) {

		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, raceService.addRace(raceDto));
		map.put(SUCCESS, true);
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@PutMapping(RACE_ID)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> updateRace(@RequestBody RaceDto raceDto, @PathVariable Integer id) {

		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, raceService.updateRace(id, raceDto));
		map.put(SUCCESS, true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> getAllReaces() {

		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, raceService.getAllRaces());
		map.put(SUCCESS, true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping(RACE_ID)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> getRaceById(@PathVariable Integer id) {

		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, raceService.getRaceById(id));
		map.put(SUCCESS, true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping(RACE_ID)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> cancelRace(@PathVariable Integer id) {

		raceService.cancelRace(id);
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, "race canceled.");
		map.put(SUCCESS, true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
