package com.atsmart.travelservice.controller;

import java.util.List;
import java.util.Optional;

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

import com.atsmart.travelservice.dto.CabDto;
import com.atsmart.travelservice.exception.TravelServiceException;
import com.atsmart.travelservice.response.TravelResponseImpl;
import com.atsmart.travelservice.service.CabService;

@RestController
@RequestMapping(path="/api/v1/cab")
@CrossOrigin
public class CabController {

	@Autowired
	CabService service;
	
	@Autowired
	TravelResponseImpl response;
	
	// CREATE
	@PostMapping("")
	public ResponseEntity<Object> insertCab(@RequestBody CabDto cabDto){
		try {
			
			service.insertCab(cabDto);
			return response.responseWithoutData("success", HttpStatus.CREATED);
			
		}catch(TravelServiceException ex) {
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// READ ALL
	@GetMapping("")
	public ResponseEntity<Object> getAllCab(){
		
		try {
			
			List<CabDto> cabDto = service.readAllCab();
			
			// No data found
			if(cabDto.isEmpty())
			{
				return response.response_CabListData(cabDto,"success", HttpStatus.OK);
			}else {
				return response.response_CabListData(cabDto, "success", HttpStatus.OK);
			}
			
		}catch(TravelServiceException ex) {
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// READ BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByCabId(@PathVariable int id){
		
		try {
			
			Optional<CabDto> cabDtoOptional = service.readCabbyId(id);
			
			if(cabDtoOptional.isPresent()) {
				CabDto cabDto = cabDtoOptional.get();
				return response.responseWithData(cabDto, "success", HttpStatus.OK);
			}else {
				return response.responseWithoutData("success", HttpStatus.NOT_FOUND);
			}
			
		}catch(TravelServiceException ex)
		{
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// UPDATE USING PATCH
	@PatchMapping("/{id}")
	public ResponseEntity<Object> updateCab(@PathVariable int id, @RequestBody CabDto cabDto){
		try {
			
			if(cabDto.getCabId()!= null) {
				
				if(service.updateCab(id, cabDto)) {
					return response.responseWithoutData("success", HttpStatus.OK);
				}else {
					return response.responseWithoutData("Cab with ID "+id+" Not Found", HttpStatus.NOT_FOUND);
				}
				
			}else {
				return response.responseWithoutData("No ID found on Request Body", HttpStatus.BAD_REQUEST);
			}
			
		}catch(TravelServiceException ex) {
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCab(@PathVariable int id){
		try {
			
			if(service.deleteCab(id)) {
				return response.responseWithoutData("success", HttpStatus.OK);
			}else {
				return response.responseWithoutData("success", HttpStatus.NOT_FOUND);
			}
			
		}catch(TravelServiceException ex) {
			return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
