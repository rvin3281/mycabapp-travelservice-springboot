package com.atsmart.travelservice.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsmart.travelservice.dto.CabDto;
import com.atsmart.travelservice.entity.Cab;
import com.atsmart.travelservice.entity.Driver;
import com.atsmart.travelservice.exception.TravelServiceException;
import com.atsmart.travelservice.mapper.CabMapper;
import com.atsmart.travelservice.repo.CabRepo;
import com.atsmart.travelservice.repo.DriverRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CabService {

	@Autowired
	CabRepo repo;
	
	@Autowired
	DriverRepo driverRepo;
	
	@Autowired
	CabMapper mapper;
	
	Logger log = Logger.getAnonymousLogger();
	
	// CREATE
	public CabDto insertCab(CabDto cabDto) {
		
		try {
			Cab cab = mapper.CabDtoToEntity(cabDto);
			cab.setStatus(1); // Status Active
			
			CabDto cabDtoResponse = mapper.cabToCabResponseDto(repo.save(cab));
			
			return cabDtoResponse;
			
		}catch(Exception ex) {
			throw new TravelServiceException("Failed to insert cab", ex);
		}
		
	}
	
	// READ
	public List<CabDto> readAllCab(){
		
		try {
			
			List<Cab> cab = repo.findAll();
			return mapper.ListEntityToCabDto(cab);
			
		}catch(Exception ex) {
			throw new TravelServiceException("Failed to retrieve all cab", ex);
		}
		
	}
	
	// READ BY ID
	public Optional<CabDto> readCabbyId(int Id) {
	
		try {
			
			Optional<Cab> cab = repo.findById(Id);
			return mapper.OptionalEntityToDto(cab);
			
		}catch(Exception ex){
			throw new TravelServiceException("Failed to retrieve cab with Id "+Id, ex);
		}
		
	}
	
	// UPDATE
	public boolean updateCab(int id, CabDto cabDto) {
		
		try {
			Optional<Cab> cabById = repo.findById(id);
			if(cabById.isPresent()) {
				
				Cab existingCab = cabById.get();
				
				if(cabDto.getCabName()!=null) {
					existingCab.setCabName(cabDto.getCabName());
				}
				if(cabDto.getCabModel() != null) {
					existingCab.setCabModel(cabDto.getCabModel());
				}
				if(cabDto.getCabPlateNum() != null) {
					existingCab.setCabPlateNum(cabDto.getCabPlateNum());
				}
				
				repo.save(existingCab);
				return true;
				
			}else {
				return false;
			}
		}catch(Exception ex) {
			throw new TravelServiceException("Failed to update cab with Id "+id, ex);
		}
	}
	
	// DELETE
	@Transactional
	public boolean deleteCab(int id) {
		
		try {
			
			Optional<Driver> driverOptional = driverRepo.checkCabOnDriver(id);
			 
			if (driverOptional.isPresent()) {
	            Driver driver = driverOptional.get();
	            driver.setCab(null); // Dissociate the cab from the driver
	            driverRepo.save(driver); // Save the driver to update the cab_id FK to null
	        }
			
			 Optional<Cab> cabOptional = repo.findById(id);
		        if (cabOptional.isPresent()) {
		            repo.deleteById(id); // Now safe to delete the cab
		            return true;
		        } else {
		            return false; // Cab not found
		        }
			
		}catch(Exception ex) {
			log.info(ex.getMessage());
			throw new TravelServiceException("Failed to delete cab with Id "+id, ex);
		}
		
	}
}
