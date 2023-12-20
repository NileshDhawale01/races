package com.nsd.race.services;

import java.util.List;

import com.nsd.race.dto.CatagoryDto;

public interface CatagoryService {

	List<CatagoryDto> getAllCatagories();
	
	CatagoryDto getCatagoryById(Integer catId);
	
	CatagoryDto addCatagory(CatagoryDto catagoryDto);
	
	CatagoryDto updateCatagory(CatagoryDto catagoryDto , Integer catId);
	
	void deleteCatagory(Integer catId);
}
