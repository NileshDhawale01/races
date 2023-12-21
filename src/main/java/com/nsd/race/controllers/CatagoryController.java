package com.nsd.race.controllers;

import static com.nsd.race.contants.ApiContants.ADD_CATAGORY;
import static com.nsd.race.contants.ApiContants.ALL_CATAGORY;
import static com.nsd.race.contants.ApiContants.CATAGORY;
import static com.nsd.race.contants.ApiContants.CATAGORY_ID;
import static com.nsd.race.contants.ApiContants.UPDATE_CATAGORY;
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

import com.nsd.race.dto.CatagoryDto;
import com.nsd.race.services.CatagoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CATAGORY)
@Slf4j
public class CatagoryController {

	@Autowired
	private CatagoryService catagoryService;

	@GetMapping(ALL_CATAGORY)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> getAllCatagories() {
		
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, catagoryService.getAllCatagories());
		map.put(SUCCESS, true);
		log.info("returning from getAllCatagories()");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping(ADD_CATAGORY)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> addCatagory(@RequestBody CatagoryDto catagoryDto){
		
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, catagoryService.addCatagory(catagoryDto));
		map.put(SUCCESS, true);
		log.info("returning from addCatagory()");
		return new ResponseEntity<>(map , HttpStatus.CREATED);
	}
	
	@PutMapping(UPDATE_CATAGORY)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object , Object>> updateCatagory(@RequestBody CatagoryDto catagoryDto , @PathVariable Integer catId){
		
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, catagoryService.updateCatagory(catagoryDto, catId));
		map.put(SUCCESS, true);
		log.info("returning from updateCatagory()");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@DeleteMapping(CATAGORY_ID)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> deleteCatagory(@PathVariable Integer id){
		
		catagoryService.deleteCatagory(id);
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, "record deleted successfully!");
		map.put(SUCCESS, true);
		log.info("returning from deleteCatagory()");
		return new ResponseEntity<>(map , HttpStatus.OK);
	}
}
