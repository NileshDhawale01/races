package com.nsd.race.services.impl;

import static com.nsd.race.mapper.CompanyMapper.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsd.race.dto.CompanyDto;
import com.nsd.race.entities.Company;
import com.nsd.race.repositories.CompanyRepo;
import com.nsd.race.services.CompaniesService;

@Service
public class CompaniesServiceImpl implements CompaniesService{

	@Autowired
	private CompanyRepo companyRepo;
	
	@Override
	public CompanyDto createCompany(CompanyDto companyDto) {
		return toCompanyDto.apply(companyRepo.save(toCompany.apply(companyDto).get())).get();
	}

	@Override
	public CompanyDto updateCompany(CompanyDto companyDto, Integer companyId) {
		Company company = companyRepo.findById(companyId).get();
		company.setName(companyDto.getName());
		company.setDescription(companyDto.getDescription());
		return toCompanyDto.apply(companyRepo.save(company)).get();
	}

	@Override
	public List<CompanyDto> getAllCompanies() {
		return toCompaniesDto.apply(companyRepo.findAll());
	}

	@Override
	public CompanyDto getCompanyById(Integer companyId) {
		return toCompanyDto.apply(companyRepo.findById(companyId).get()).get();
	}

	@Override
	public void deleteComapanyById(Integer companyId) {
		Company company = companyRepo.findById(companyId).get();
		companyRepo.delete(company);
		
	}

}
