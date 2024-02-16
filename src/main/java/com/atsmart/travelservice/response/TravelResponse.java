package com.atsmart.travelservice.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.atsmart.travelservice.dto.CabDto;
import com.atsmart.travelservice.dto.Travel.TravelResponseDto;
import com.atsmart.travelservice.dto.driver.DriverResponseDto;
import com.atsmart.travelservice.entity.Travel;

public interface TravelResponse {

	public ResponseEntity<Object> responseWithoutData (String message, HttpStatus httpStatus);
	
	public ResponseEntity<Object> responseWithData (CabDto cabDto,String message, HttpStatus httpStatus);
	
	public ResponseEntity<Object> response_CabListData(List<CabDto> cabDto, String message, HttpStatus httpStatus);
	
	public ResponseEntity<Object> response_DriverListData(List<DriverResponseDto> driverResponseDto, String message, HttpStatus httpStatus);
	
	public ResponseEntity<Object> errorResponse(String message, String errorMessage, HttpStatus httpStatus);
	
	public ResponseEntity<Object> resposne_DriverData(DriverResponseDto responseDto, String message, HttpStatus httpStatus);
	
	public ResponseEntity<Object> response_Travel_With_Data(TravelResponseDto travelResonseDto, String message, HttpStatus httpStatus);
	
	public ResponseEntity<Object> response_Travel_List_Data(List<TravelResponseDto> travelResponseDto, String message, HttpStatus httpStatus);
	
	public ResponseEntity<Object> response_Travel_List_Data_Original(List<Travel> travels, String message, HttpStatus httpStatus);
}
