package com.atsmart.travelservice.dto.Travel;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelRegisterDto {

	private String travelSource;
	private String travelDestination;
	private String travel_time;
	private String travelCost;
	private Date travelDate;
	private Integer cabId;
	private Integer driverId;
	
}
