package com.atsmart.travelservice.dto.Travel;

import java.sql.Date;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelUpdateDto {

	private Integer travelId;
	private String travelSource;
	private String travelDestination;
	private LocalTime travel_time;
	private Date travelDate;
	private String travelCost;
	private Integer driverId;
	private Integer cabId;
	
}
