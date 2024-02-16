package com.atsmart.travelservice.dto.Travel;

import java.sql.Date;

import com.atsmart.travelservice.dto.CabDto;
import com.atsmart.travelservice.dto.driver.DriverResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelResponseDto {

	private Integer travelId;
	private String travelSource;
	private String travelDestination;
	private String travel_time;
	private Date travelDate;
	private String travelCost;
	private DriverResponseDto driver;
	private CabDto cab;
	
}
