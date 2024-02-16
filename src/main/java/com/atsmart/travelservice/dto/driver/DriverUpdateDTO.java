package com.atsmart.travelservice.dto.driver;

import com.atsmart.travelservice.entity.Cab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverUpdateDTO {

	private Integer driverId;
	private String driverName;
	private String driverContact;
	private String driverIdentificationNum;
	private Integer cabId;
	
}
