package com.nsd.race.services.impl;

import static com.nsd.race.mapper.CompanyMapper.toCompaniesDto;
import static com.nsd.race.mapper.CompanyMapper.toCompany;
import static com.nsd.race.mapper.CompanyMapper.toCompanyDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsd.race.dto.CompanyDto;
import com.nsd.race.entities.Company;
import com.nsd.race.exceptions.ResorcesNotFoundException;
import com.nsd.race.repositories.CompanyRepo;
import com.nsd.race.services.CompaniesService;

@Service
public class CompaniesServiceImpl implements CompaniesService{

	@Autowired
	private CompanyRepo companyRepo;
	
	@Override
	public CompanyDto createCompany(CompanyDto companyDto) {
		return toCompanyDto.apply(companyRepo.save(toCompany.apply(companyDto).get())).orElseThrow(()->new ResorcesNotFoundException("compony", "company conversion error"));
	}

	@Override
	public CompanyDto updateCompany(CompanyDto companyDto, Integer companyId) {
		Company company = companyRepo.findById(companyId).orElseThrow(()->new ResorcesNotFoundException("compony", "companyId",companyId));
		company.setName(companyDto.getName());
		company.setDescription(companyDto.getDescription());
		return toCompanyDto.apply(companyRepo.save(company)).orElseThrow(()->new ResorcesNotFoundException("compony", "company conversion error"));
	}

	@Override
	public List<CompanyDto> getAllCompanies() {
		return toCompaniesDto.apply(companyRepo.findAll());
	}

	@Override
	public CompanyDto getCompanyById(Integer companyId) {
		return toCompanyDto.apply(companyRepo.findById(companyId).get()).orElseThrow(()->new ResorcesNotFoundException("compony", "companyId",companyId));
	}

	@Override
	public void deleteComapanyById(Integer companyId) {
		Company company = companyRepo.findById(companyId).orElseThrow(()->new ResorcesNotFoundException("compony", "companyId",companyId));
		companyRepo.delete(company);
		
	}

}
