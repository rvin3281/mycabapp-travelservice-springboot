package com.atsmart.travelservice.dto.driver;

import com.atsmart.travelservice.dto.CabDto;
import com.atsmart.travelservice.entity.Cab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponseDto {

	private Integer driverId;
	private String driverName;
	private String driverContact;
	private String driverIdentificationNum;
	
	// To output For CAB we need to use the DTO
	private CabDto cab; // FK
	
}
