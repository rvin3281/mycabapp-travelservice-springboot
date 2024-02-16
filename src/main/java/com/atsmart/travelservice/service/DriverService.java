package com.atsmart.travelservice.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsmart.travelservice.dto.driver.DriverRegisterDto;
import com.atsmart.travelservice.dto.driver.DriverResponseDto;
import com.atsmart.travelservice.dto.driver.DriverUpdateDTO;
import com.atsmart.travelservice.entity.Cab;
import com.atsmart.travelservice.entity.Driver;
import com.atsmart.travelservice.exception.TravelServiceException;
import com.atsmart.travelservice.mapper.DriverMapper;
import com.atsmart.travelservice.repo.CabRepo;
import com.atsmart.travelservice.repo.DriverRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DriverService {

	Logger log = Logger.getAnonymousLogger();
	
	@Autowired
	DriverRepo repo;
	
	@Autowired
	DriverMapper mapper;
	
	@Autowired
	CabRepo cabRepo;
	
	public DriverResponseDto insertDriverService(DriverRegisterDto driverRegisterDto)
	{
		try {
			
			if(driverRegisterDto.getCabId() == -1) {
				Driver driver = mapper.DriverRegisterDtoToEntity(driverRegisterDto);
				driver.setStatus(1);
				driver.setCab(null);
				
				return mapper.driverToDriverResponseDto(repo.save(driver));
			}
			else {
				int cabId = driverRegisterDto.getCabId().intValue();
				Cab cab = cabRepo.findById(cabId).orElseThrow(()->new EntityNotFoundException("Cab ID not found "+cabId));
				
				Driver driver = mapper.DriverRegisterDtoToEntity(driverRegisterDto);
				driver.setStatus(1);
				driver.setCab(cab);
				
				return mapper.driverToDriverResponseDto(repo.save(driver));
			}
			
			
		}catch(Exception ex) {
			log.info(ex.getMessage());
			throw new TravelServiceException("failed to insert new driver",ex);
		}
	}

	public List<DriverResponseDto> getAllDriver(){
		
		try {
			List<Driver> driver = repo.findAll();
			List<DriverResponseDto> driverResponse = mapper.toDtoList(driver);
		
			return driverResponse;
			
		}catch(Exception ex) {
			log.info(ex.getMessage());
			throw new TravelServiceException("Failed to get all Driver List",ex);
		}
		
	}
	
	public DriverResponseDto getDriver(int id){
		
		try {
			
			DriverResponseDto driverResponseDto = mapper.driverToDriverResponseDto(repo.findById(id).orElseThrow(()-> new EntityNotFoundException("No Driver Found")));
			return driverResponseDto;
			
		}catch(Exception ex) {
			throw new TravelServiceException("Failed to get Driver",ex);
		}
		
	}
	
	public boolean updateDriver(DriverUpdateDTO driverUpdateDto, int id)
	{
		try {
			
			Driver driver = repo.findById(id).orElseThrow(()->new EntityNotFoundException("No Driver Found"));
			
			if(driverUpdateDto.getDriverId().intValue() == driver.getDriverId().intValue())
			{
				
				if(driverUpdateDto.getDriverName()!=null)
				{
					driver.setDriverName(driverUpdateDto.getDriverName());
				}
				if(driverUpdateDto.getDriverIdentificationNum()!=null)
				{
					driver.setDriverIdentificationNum(driverUpdateDto.getDriverIdentificationNum());
				}
				if(driverUpdateDto.getDriverContact()!=null)
				{
					driver.setDriverContact(driverUpdateDto.getDriverContact());
				}
				if(driverUpdateDto.getCabId() != null) {
				    if(driverUpdateDto.getCabId() == -1) {
				        driver.setCab(null);
				    } else {
				        Cab cab = cabRepo.findById(driverUpdateDto.getCabId())
				                         .orElseThrow(() -> new EntityNotFoundException("Cab ID not found " + driverUpdateDto.getCabId()));
				        driver.setCab(cab);
				    }
				}
				
				repo.save(driver);
				return true;
				
			}else {
				throw new TravelServiceException("ID in Request Body and URL not Matching");
			}
			
			
		}catch(Exception ex)
		{
			log.info(ex.getMessage());
			throw new TravelServiceException("Failed to update driver with Id "+id, ex);
		}
	}
	
	public boolean deleteDriver(int id)
	{
		try {
			
			repo.findById(id).orElseThrow(()-> new EntityNotFoundException("Driver not found"));
			repo.deleteById(id);
			
			if(repo.findById(id).isEmpty())
			{
				return true;
			}else {
				return false;
			}
			
		}catch(Exception ex)
		{
			throw new TravelServiceException("Failed to delete driver with Id "+id, ex);
		}
	}
	
}
