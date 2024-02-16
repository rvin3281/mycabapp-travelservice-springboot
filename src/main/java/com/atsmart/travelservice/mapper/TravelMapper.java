package com.atsmart.travelservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.atsmart.travelservice.dto.Travel.TravelRegisterDto;
import com.atsmart.travelservice.dto.Travel.TravelResponseDto;
import com.atsmart.travelservice.entity.Travel;

@Mapper(componentModel="spring")
public interface TravelMapper {

	public Travel travelRegisterDtoToEntity (TravelRegisterDto travelRegisterDto);
	
	public TravelResponseDto entityToTravelResponseDto (Travel travel);
	
	public List<TravelResponseDto> entitiesToTravelRegisterDtos (List<Travel> travel);
	
}
