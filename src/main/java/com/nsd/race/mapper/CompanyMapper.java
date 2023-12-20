package com.nsd.race.mapper;

import static com.nsd.race.util.CollectionUtil.newList;
import static com.nsd.race.util.FunctionUtil.evalMapper;
import static com.nsd.race.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.nsd.race.dto.CompanyDto;
import com.nsd.race.entities.Company;

public class CompanyMapper {

	private CompanyMapper() {}
	
	public static final Function<Company, Optional<CompanyDto>> toCompanyDto = e-> evalMapper(e, CompanyDto.class);
	
	public static final Function<CompanyDto, Optional<Company>> toCompany = e-> evalMapper(e, Company.class);
	
	public static final Function<Collection<Company>, List<CompanyDto>> toCompaniesDto = e-> newList(evalMapperCollection(e, CompanyDto.class));
	
	public static final Function<Collection<CompanyDto>, List<Company>> toCompanies = e-> newList(evalMapperCollection(e, Company.class));
}
