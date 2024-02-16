package com.atsmart.travelservice.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.atsmart.travelservice.dto.driver.DriverRegisterDto;
import com.atsmart.travelservice.dto.driver.DriverResponseDto;
import com.atsmart.travelservice.entity.Driver;



@Mapper(componentModel ="spring")
public interface DriverMapper {

	 DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);
	
	Driver DriverResponseDtoToEntity (DriverResponseDto driverResponseDto);
	
	Driver DriverRegisterDtoToEntity (DriverRegisterDto driverRegisterDto);
	
	 DriverResponseDto driverToDriverResponseDto(Driver driver);

	 List<DriverResponseDto> toDtoList(List<Driver> driver); 
	 
//	 List<DriverResponseDto> driversToDriverResponseDtos(List<Driver> drivers);
	
	default Optional<DriverResponseDto> Entity_To_Optional_DriverResponse(Optional<Driver> driver)
	{
		return driver.map(this::driverToDriverResponseDto);
	}
	
}
