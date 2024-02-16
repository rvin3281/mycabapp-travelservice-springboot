package com.atsmart.travelservice.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.atsmart.travelservice.dto.CabDto;
import com.atsmart.travelservice.dto.Travel.TravelResponseDto;
import com.atsmart.travelservice.dto.driver.DriverResponseDto;
import com.atsmart.travelservice.entity.Travel;

@Component
public class TravelResponseImpl implements TravelResponse {

	@Override
	public ResponseEntity<Object> responseWithoutData(String message, HttpStatus httpStatus) {
		
		Map<String, Object> response = new HashMap();
		response.put("message", message);
		response.put("status", httpStatus.value());
		
		return new ResponseEntity<>(response, httpStatus);
		
	}

	@Override
	public ResponseEntity<Object> responseWithData(CabDto cabDto, String message, HttpStatus httpStatus) {
		Map<String,Object> response = new HashMap();
		response.put("message", message);
		response.put("data", cabDto);
		response.put("status", httpStatus);
		
		return new ResponseEntity<>(response, httpStatus);
	}

	@Override
	public ResponseEntity<Object> response_CabListData(List<CabDto> cabDto, String message, HttpStatus httpStatus) {
		
		Map<String,Object> response = new HashMap();
		response.put("message", message);
		response.put("data", cabDto);
		response.put("status", httpStatus);
		
		return new ResponseEntity<>(response,httpStatus);
		
	}
	
	@Override
	public ResponseEntity<Object> response_DriverListData(List<DriverResponseDto> driverResponseDto, String message, HttpStatus httpStatus) {
		
		Map<String,Object> response = new HashMap();
		response.put("message", message);
		response.put("data", driverResponseDto);
		response.put("status", httpStatus);
		
		return new ResponseEntity<>(response,httpStatus);
		
	}

	@Override
	public ResponseEntity<Object> errorResponse(String message, String errorMessage, HttpStatus httpStatus) {
		Map<String, Object> response = new HashMap();
		response.put("message", message);
		response.put("errorMsg", errorMessage);
		
		return new ResponseEntity<>(response, httpStatus);
	}

	@Override
	public ResponseEntity<Object> resposne_DriverData(DriverResponseDto responseDto, String message,HttpStatus httpStatus)
	{
			
			Map<String,Object> response = new HashMap();
			response.put("data", responseDto);
			response.put("message", message);
			response.put("status", httpStatus.value());
	
			return new ResponseEntity<>(response, httpStatus);
	}

	@Override
	public ResponseEntity<Object> response_Travel_With_Data(TravelResponseDto travelResonseDto, String message,
			HttpStatus httpStatus) {
		
		Map<String,Object> response = new HashMap();
		response.put("data", travelResonseDto);
		response.put("message", message);
		response.put("status", httpStatus.value());
		
		return new ResponseEntity<>(response,httpStatus);
		
	}

	@Override
	public ResponseEntity<Object> response_Travel_List_Data(List<TravelResponseDto> travelResponseDto, String message,
			HttpStatus httpStatus) {
		
		Map<String,Object> response = new HashMap();
		response.put("data", travelResponseDto);
		response.put("message", message);
		response.put("status", httpStatus.value());
		
		return new ResponseEntity<>(response,httpStatus);
	}

	@Override
	public ResponseEntity<Object> response_Travel_List_Data_Original(List<Travel> travels, String message,
			HttpStatus httpStatus) {
		Map<String,Object> response = new HashMap();
		response.put("data", travels);
		response.put("message", message);
		response.put("status", httpStatus.value());
		
		return new ResponseEntity<>(response,httpStatus);
	}

}
