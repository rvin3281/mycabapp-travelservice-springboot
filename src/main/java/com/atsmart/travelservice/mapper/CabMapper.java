package com.atsmart.travelservice.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.atsmart.travelservice.dto.CabDto;
import com.atsmart.travelservice.entity.Cab;

@Mapper(componentModel = "spring")
public interface CabMapper {

	CabMapper INSTANCE = Mappers.getMapper(CabMapper.class);
	
	CabDto cabToCabResponseDto (Cab cab);
	
	Cab CabDtoToEntity (CabDto cabDto);
	
	List<CabDto> ListEntityToCabDto (List<Cab> cab);
	
	
	default Optional<CabDto> OptionalEntityToDto (Optional<Cab> cab){
		return cab.map(this::cabToCabResponseDto);
	}
}
