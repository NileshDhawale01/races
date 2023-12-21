package com.nsd.race.services.impl;

import static com.nsd.race.mapper.CatagoryMapper.toCatagory;
import static com.nsd.race.mapper.CatagoryMapper.toCatagoryDto;
import static com.nsd.race.mapper.CatagoryMapper.toCatagoryDtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsd.race.dto.CatagoryDto;
import com.nsd.race.entities.Catagory;
import com.nsd.race.exceptions.ResorcesNotFoundException;
import com.nsd.race.repositories.CatagoryRepo;
import com.nsd.race.services.CatagoryService;

@Service
public class CatagoryServiceImpl implements CatagoryService{

	@Autowired
	private CatagoryRepo catagoryRepo;
	
	@Override
	public List<CatagoryDto> getAllCatagories() {
		return toCatagoryDtos.apply(catagoryRepo.findAll());
	}

	@Override
	public CatagoryDto getCatagoryById(Integer catId) {
		return toCatagoryDto.apply(catagoryRepo.findById(catId).get()).orElseThrow(()->new ResorcesNotFoundException("Catagory","CatagoryId",catId));
	}

	@Override
	public CatagoryDto addCatagory(CatagoryDto catagoryDto) {
		return toCatagoryDto.apply(catagoryRepo.save(toCatagory.apply(catagoryDto).get())).orElseThrow(()->new ResorcesNotFoundException("Catagory","catagory converson error"));
	}

	@Override
	public CatagoryDto updateCatagory(CatagoryDto catagoryDto, Integer catId) {
		CatagoryDto catagoryDto2 = toCatagoryDto.apply(catagoryRepo.findById(catId).get()).orElseThrow(()->new ResorcesNotFoundException("Catagory","catagory converson error"));
		catagoryDto2.setName(catagoryDto.getName());
		catagoryDto2.setDescription(catagoryDto.getDescription());
		return toCatagoryDto.apply(catagoryRepo.save(toCatagory.apply(catagoryDto2).get())).orElseThrow(()->new ResorcesNotFoundException("Catagory","catagory converson error"));
	}

	@Override
	public void deleteCatagory(Integer catId) {
		Catagory catagory = catagoryRepo.findById(catId).orElseThrow(()->new ResorcesNotFoundException("Catagory","CatagoryId",catId));
		catagoryRepo.delete(catagory);
	}

}
