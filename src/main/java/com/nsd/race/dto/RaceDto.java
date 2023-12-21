package com.nsd.race.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceDto {

	private Integer id;
	
	private String country;
	
	private String date;
	
	private String track;
	
	private String description;
	
	private CarDto carsDto;
	
//	private CompanyDto companyDto;
	
	private CatagoryDto catagoryDto;
}
