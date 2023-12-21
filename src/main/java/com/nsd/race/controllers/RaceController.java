package com.nsd.race.controllers;

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
@RequestMapping("/race")
public class RaceController {

	@Autowired
	private RaceService raceService;

	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> addRace(@RequestBody RaceDto raceDto) {

		Map<Object, Object> map = new HashMap<>();
		map.put("data", raceService.addRace(raceDto));
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> updateRace(@RequestBody RaceDto raceDto, @PathVariable Integer id) {

		Map<Object, Object> map = new HashMap<>();
		map.put("data", raceService.updateRace(id, raceDto));
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> getAllReaces() {

		Map<Object, Object> map = new HashMap<>();
		map.put("data", raceService.getAllRaces());
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> getRaceById(@PathVariable Integer id) {

		Map<Object, Object> map = new HashMap<>();
		map.put("data", raceService.getRaceById(id));
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> cancelRace(@PathVariable Integer id) {

		raceService.cancelRace(id);
		Map<Object, Object> map = new HashMap<>();
		map.put("data", "race canceled.");
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
