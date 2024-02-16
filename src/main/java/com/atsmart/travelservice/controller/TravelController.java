package com.atsmart.travelservice.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atsmart.travelservice.dto.Travel.TravelRegisterDto;
import com.atsmart.travelservice.dto.Travel.TravelResponseDto;
import com.atsmart.travelservice.dto.Travel.TravelUpdateDto;
import com.atsmart.travelservice.entity.Travel;
import com.atsmart.travelservice.response.TravelResponseImpl;
import com.atsmart.travelservice.service.TravelService;

@RestController
@RequestMapping(path ="/api/v1/travel")
@CrossOrigin
public class TravelController {

	@Autowired
	TravelService service;
	
	@Autowired
	TravelResponseImpl response;
	
	@PostMapping("")
	public ResponseEntity<Object> insertTravel (@RequestBody TravelRegisterDto travelRegisterDto)
	{
		try {
			
			TravelResponseDto travelResponseDto = service.insertTravel(travelRegisterDto);
			return response.responseWithoutData("success", HttpStatus.CREATED);
			
		}catch(Exception ex)
		{
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getAllTravel(){
		try {
			
			List<TravelResponseDto> travelResponseDto = service.getAllTravel();
			return response.response_Travel_List_Data(travelResponseDto, "success", HttpStatus.OK);
			
		}catch(Exception ex)
		{
			return response.errorResponse("fail", "Failed to Get ALl Travel", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getTravelById(@PathVariable Integer id){
		try {
			
			TravelResponseDto travelResponseDto = service.getTravelById(id.intValue());
			return response.response_Travel_With_Data(travelResponseDto, "success", HttpStatus.OK);
			
		}catch(Exception ex) {
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Object> updateTravel(@RequestBody TravelUpdateDto travelUpdateDto, @PathVariable int id){
		
		try {
			
			if(travelUpdateDto.getTravelId() == null) {
				
				return response.errorResponse("fail", "No ID on request Body", HttpStatus.BAD_REQUEST);
			}
			if(travelUpdateDto.getTravelId().intValue() != id) {
				return response.errorResponse("fail", "ID on URL and request body is different", HttpStatus.BAD_REQUEST);
			}
			
			if(service.updateTravel(travelUpdateDto, id)) {
				
				return response.responseWithoutData("success", HttpStatus.OK);
				
			}else {
				throw new Error("Unable to Update for Travel");
			}
			
			
		}catch(Exception ex) {
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTravel(@PathVariable int id)
	{
		try {
			
			if(service.deleteTravel(id)) {
				return response.responseWithoutData("success", HttpStatus.OK);
			}else {
				return response.responseWithoutData("success", HttpStatus.BAD_REQUEST);
			}

			
		}catch(Exception ex) {
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	// MICROSERVICE
	@GetMapping("/check")
	public ResponseEntity<Object> checkAvailability(
			@RequestParam(name = "source") String source,
            @RequestParam(name = "destination") String destination
			){
		
		try {
			List<TravelResponseDto> travelResponseDto = service.checkAvailable(source, destination);
			return response.response_Travel_List_Data(travelResponseDto, "success", HttpStatus.OK);
				
		}catch(Exception ex) {
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}




