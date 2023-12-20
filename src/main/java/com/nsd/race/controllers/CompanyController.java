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

import com.nsd.race.dto.CompanyDto;
import com.nsd.race.services.CompaniesService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompaniesService companiesService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> getAllCompanies(){
		
		Map<Object, Object> map = new HashMap<>();
		map.put("data", companiesService.getAllCompanies());
		map.put("success", true);
		return new ResponseEntity<>(map , HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> createCompany(@RequestBody CompanyDto companyDto){
		
		Map<Object, Object> map = new HashMap<>();
		map.put("data", companiesService.createCompany(companyDto));
		map.put("success", true);
		return new ResponseEntity<>(map , HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> updateCompany(@RequestBody CompanyDto companyDto ,@PathVariable Integer id ){
		
		Map<Object, Object> map = new HashMap<>();
		map.put("data", companiesService.updateCompany(companyDto, id));
		map.put("success", true);
		return new ResponseEntity<>(map , HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> getCompanyById(@PathVariable Integer id){
		
		Map<Object, Object> map = new HashMap<>();
		map.put("data", companiesService.getCompanyById(id));
		map.put("success", true);
		return new ResponseEntity<>(map , HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> deleteCompany(@PathVariable Integer id){
		
		companiesService.deleteComapanyById(id);
		Map<Object, Object> map = new HashMap<>();
		map.put("data", "company deleted.");
		map.put("success", true);
		return new ResponseEntity<>(map , HttpStatus.OK);
		
	}
}
