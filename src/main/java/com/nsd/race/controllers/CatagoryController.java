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

import com.nsd.race.dto.CatagoryDto;
import com.nsd.race.services.CatagoryService;

@RestController
@RequestMapping("/catagory")
public class CatagoryController {

	@Autowired
	private CatagoryService catagoryService;

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> getAllCatagories() {
		
		Map<Object, Object> map = new HashMap<>();
		map.put("Data", catagoryService.getAllCatagories());
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> addCatagory(@RequestBody CatagoryDto catagoryDto){
		
		Map<Object, Object> map = new HashMap<>();
		map.put("data", catagoryService.addCatagory(catagoryDto));
		map.put("success", true);
		return new ResponseEntity<>(map , HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{catId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object , Object>> updateCatagory(@RequestBody CatagoryDto catagoryDto , @PathVariable Integer catId){
		
		Map<Object, Object> map = new HashMap<>();
		map.put("data", catagoryService.updateCatagory(catagoryDto, catId));
		map.put("success", true);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> deleteCatagory(@PathVariable Integer id){
		
		catagoryService.deleteCatagory(id);
		Map<Object, Object> map = new HashMap<>();
		map.put("data", "record deleted successfully!");
		map.put("success", true);
		return new ResponseEntity<>(map , HttpStatus.OK);
	}
}
