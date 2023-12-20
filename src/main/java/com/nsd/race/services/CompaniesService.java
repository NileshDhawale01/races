package com.nsd.race.services;

import java.util.List;

import com.nsd.race.dto.CompanyDto;

public interface CompaniesService {

	CompanyDto createCompany(CompanyDto companyDto);
	
	CompanyDto updateCompany(CompanyDto companyDto , Integer companyId);
	
	List<CompanyDto> getAllCompanies();
	
	CompanyDto getCompanyById(Integer companyId);
	
	void deleteComapanyById(Integer companyId);
}
