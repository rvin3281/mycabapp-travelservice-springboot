package com.atsmart.travelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabDto {

	private Integer cabId;
	private String cabModel;
	private String cabName;
	private String cabPlateNum;
	
}
