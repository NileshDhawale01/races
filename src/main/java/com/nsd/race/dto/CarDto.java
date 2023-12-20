package com.nsd.race.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

	private Integer id;
	
	private String name;
	
	private String model;
	
	private String price;
	
	private String topSpeed;
	
	private CompanyDto companyDto;
}
