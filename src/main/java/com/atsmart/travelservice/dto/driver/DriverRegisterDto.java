package com.atsmart.travelservice.dto.driver;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverRegisterDto {

	private String driverName;
	private String driverContact;
	private String driverIdentificationNum;
	private Integer cabId; // FK
	
}
