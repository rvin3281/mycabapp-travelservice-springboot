package com.atsmart.travelservice.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atsmart.travelservice.dto.driver.DriverRegisterDto;
import com.atsmart.travelservice.dto.driver.DriverResponseDto;
import com.atsmart.travelservice.dto.driver.DriverUpdateDTO;
import com.atsmart.travelservice.exception.TravelServiceException;
import com.atsmart.travelservice.response.TravelResponse;
import com.atsmart.travelservice.service.DriverService;

@RestController
@RequestMapping(path="/api/v1/driver")
@CrossOrigin
public class DriverController {

	Logger log = Logger.getAnonymousLogger();
	
	@Autowired
	DriverService service;
	
	@Autowired 
	TravelResponse response;
	
	
	@PostMapping("")
	public ResponseEntity<Object> insertDriver(@RequestBody DriverRegisterDto driverDto)
	{
		try {
		
			service.insertDriverService(driverDto);
			return response.responseWithoutData("success", HttpStatus.CREATED);
			
		}catch(TravelServiceException ex)
		{
			log.info(ex.getStackTrace().toString());
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getAllDriver()
	{
		try {
			
			List<DriverResponseDto> driverResponseDto = service.getAllDriver();
			return response.response_DriverListData(driverResponseDto, "success", HttpStatus.OK);
			
		}catch(TravelServiceException ex)
		{
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getDriver(@PathVariable int id)
	{
		try {
			
			DriverResponseDto responseDto = service.getDriver(id);
			
			return response.resposne_DriverData(responseDto, "success", HttpStatus.OK);
			
		}catch(TravelServiceException ex)
		{
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Object> updateDriver(@RequestBody DriverUpdateDTO driverUpdateDto, @PathVariable Integer id)
	{
		try {
			
			if(id == null || id == 0)
			{
				return response.errorResponse("success", "Please check ID on URL", HttpStatus.BAD_REQUEST);
			}
			if(driverUpdateDto.getDriverId()==null || driverUpdateDto.getDriverId()==0) {
				
				return response.errorResponse("success", "Please check ID on Request Body", HttpStatus.BAD_REQUEST);	
			}
			
			if(service.updateDriver(driverUpdateDto, id.intValue())) {
				return response.responseWithoutData("success", HttpStatus.OK);
			}else {
				return response.errorResponse("success", "Not able to update", HttpStatus.BAD_REQUEST);
			}
	
		}catch(Exception ex) {
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDriver(@PathVariable int id){
		
		try {
			if(service.deleteDriver(id)) {
				return response.responseWithoutData("success", HttpStatus.OK);
			}else {
				return response.responseWithoutData("success", HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception ex)
		{
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
