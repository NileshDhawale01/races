package com.nsd.race.mapper;

import static com.nsd.race.util.CollectionUtil.newList;
import static com.nsd.race.util.FunctionUtil.evalMapper;
import static com.nsd.race.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.nsd.race.dto.CatagoryDto;
import com.nsd.race.entities.Catagory;

public class CatagoryMapper {
	
	private CatagoryMapper() {}

	public static final Function<Catagory, Optional<CatagoryDto>> toCatagoryDto = e-> evalMapper(e, CatagoryDto.class);
	
	public static final Function<CatagoryDto, Optional<Catagory>> toCatagory = e-> evalMapper(e, Catagory.class);
	
	public static final Function<Collection<Catagory>, List<CatagoryDto>> toCatagoryDtos = e-> newList(evalMapperCollection(e, CatagoryDto.class));
	
	public static final Function<Collection<CatagoryDto>, List<Catagory>> toCatagories = e-> newList(evalMapperCollection(e, Catagory.class));
}
