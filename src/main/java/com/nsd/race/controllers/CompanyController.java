package com.nsd.race.controllers;

import static com.nsd.race.contants.ApiContants.COMPANY_ID;
import static com.nsd.race.contants.ApiContants.COMPANY;
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

import com.nsd.race.dto.CompanyDto;
import com.nsd.race.services.CompaniesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(COMPANY)
@Slf4j
public class CompanyController {

	@Autowired
	private CompaniesService companiesService;
	
	@GetMapping
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> getAllCompanies(){
		
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, companiesService.getAllCompanies());
		map.put(SUCCESS, true);
		log.info("returning from getAllCompanies()");
		return new ResponseEntity<>(map , HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> createCompany(@RequestBody CompanyDto companyDto){
		
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, companiesService.createCompany(companyDto));
		map.put(SUCCESS, true);
		log.info("returning from createCompany()");
		return new ResponseEntity<>(map , HttpStatus.CREATED);
	}
	
	@PutMapping(COMPANY_ID)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> updateCompany(@RequestBody CompanyDto companyDto ,@PathVariable Integer id ){
		
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, companiesService.updateCompany(companyDto, id));
		map.put(SUCCESS, true);
		log.info("returning from updateCompany()");
		return new ResponseEntity<>(map , HttpStatus.OK);
	}
	
	@GetMapping(COMPANY_ID)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> getCompanyById(@PathVariable Integer id){
		
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, companiesService.getCompanyById(id));
		map.put(SUCCESS, true);
		log.info("returning from getCompanyById()");
		return new ResponseEntity<>(map , HttpStatus.OK);
	}
	
	@DeleteMapping(COMPANY_ID)
	@PreAuthorize(ADMIN_ATHORITY)
	public ResponseEntity<Map<Object, Object>> deleteCompany(@PathVariable Integer id){
		
		companiesService.deleteComapanyById(id);
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, "company deleted.");
		map.put(SUCCESS, true);
		log.info("returning from deleteCompany()");
		return new ResponseEntity<>(map , HttpStatus.OK);
		
	}
}
