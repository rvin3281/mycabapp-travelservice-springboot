package com.atsmart.travelservice.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsmart.travelservice.dto.Travel.TravelRegisterDto;
import com.atsmart.travelservice.dto.Travel.TravelResponseDto;
import com.atsmart.travelservice.dto.Travel.TravelUpdateDto;
import com.atsmart.travelservice.entity.Cab;
import com.atsmart.travelservice.entity.Driver;
import com.atsmart.travelservice.entity.Travel;
import com.atsmart.travelservice.exception.TravelServiceException;
import com.atsmart.travelservice.mapper.TravelMapper;
import com.atsmart.travelservice.repo.CabRepo;
import com.atsmart.travelservice.repo.DriverRepo;
import com.atsmart.travelservice.repo.TravelRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TravelService {

	@Autowired
	TravelRepo repo;
	
	@Autowired
	DriverRepo driverRepo;
	
	@Autowired
	CabRepo cabRepo;
	
	@Autowired
	TravelMapper mapper;
	
	Logger log = Logger.getAnonymousLogger();
	
	public TravelResponseDto insertTravel (TravelRegisterDto travelRegisterDto)
	{
		try {
			int driverId = travelRegisterDto.getDriverId().intValue();
			Driver driver = driverRepo.findById(driverId).orElseThrow(()->  new EntityNotFoundException("No Driver Found! Cannot Insert Travel"));
			
			int cabId = travelRegisterDto.getCabId().intValue();
			
			if(cabId != driver.getCab().getCabId().intValue()) {
				throw new TravelServiceException("Cab not matching with driver");
			}
			
			Cab cab = cabRepo.findById(cabId).orElseThrow(()-> new EntityNotFoundException("No Cab Found! Cannot Insert Travel"));
			
			
			Travel travel = mapper.travelRegisterDtoToEntity(travelRegisterDto);
			
			travel.setStatus(1);
			travel.setCab(cab);
			travel.setDriver(driver);
			
			TravelResponseDto travelResponseDto = mapper.entityToTravelResponseDto(repo.save(travel));
			return travelResponseDto;
			
		}catch(Exception ex)
		{
			log.info(ex.getMessage());
			throw new TravelServiceException("Fail to insert Travel", ex);
		}
	}
	
	public List<TravelResponseDto> getAllTravel ()
	{
		try {
			
			List<TravelResponseDto> travelResponseDto = mapper.entitiesToTravelRegisterDtos(repo.findAll());
			return travelResponseDto;
			
		}catch(Exception ex)
		{
			throw new TravelServiceException("Fail to get all travel", ex);
		}
	}
	
	public TravelResponseDto getTravelById(int id) {
		
		try {
			
			Travel travel = repo.findById(id).orElseThrow(()->new EntityNotFoundException("Travel Not Found"));
		
			TravelResponseDto travelResponseDto = mapper.entityToTravelResponseDto(travel);
			return travelResponseDto;
			
		}catch(Exception ex) {
			throw new TravelServiceException("Failed to get Travel by ID "+id);
		}
		
	}
	
	public boolean updateTravel(TravelUpdateDto travelUpdateDto, int id)
	{
		try {
			
			Travel travel = repo.findById(id).orElseThrow(()->new EntityNotFoundException("Travel not Found"));
			
			if(travelUpdateDto.getTravelId().intValue() == id) {
				
				if(travelUpdateDto.getTravelSource()!=null) {
					travel.setTravelSource(travelUpdateDto.getTravelSource());
				}
				if(travelUpdateDto.getTravelDestination()!=null) {
					travel.setTravelDestination(travelUpdateDto.getTravelDestination());
				}
				if(travelUpdateDto.getTravel_time()!=null)
				{
					travel.setTravel_time(travelUpdateDto.getTravel_time());
				}
				if(travelUpdateDto.getTravelDate()!=null) {
					travel.setTravelDate(travelUpdateDto.getTravelDate());
				}
				if(travelUpdateDto.getTravelCost()!=null) {
					travel.setTravelCost(travelUpdateDto.getTravelCost());
				}
				if(travelUpdateDto.getDriverId()!=null) {
					Driver driver = driverRepo.findById(travelUpdateDto.getDriverId().intValue()).orElseThrow(()->new EntityNotFoundException("Driver Not Found"));
					
					travel.setDriver(driver);
				}
				if(travelUpdateDto.getCabId()!=null) {
					Cab cab = cabRepo.findById(travelUpdateDto.getCabId().intValue()).orElseThrow(()->new EntityNotFoundException("Cab Not Found"));
					travel.setCab(cab);
				}
				
				repo.save(travel);
				return true;
				
			}else {

				throw new TravelServiceException("ID in Request Body and URL not Matching");
			}
			
		}catch(Exception ex) {
			log.info(ex.getMessage());
			throw new TravelServiceException(ex.getMessage(), ex);
		}
	}
	
	public boolean deleteTravel(int id) {
		try {
			
			repo.findById(id).orElseThrow(()-> new EntityNotFoundException("Travel not found"));
			repo.deleteById(id);
			
			if(repo.findById(id).isEmpty())
			{
				return true;
			}else {
				return false;
			}
			
		}catch(Exception ex) {
			throw new TravelServiceException("Failed to delete travel with ID "+id);
		}
	}

	public List<TravelResponseDto> checkAvailable(String source, String destination) {
		
		try {
			List<TravelResponseDto> travelResponseDto = mapper.entitiesToTravelRegisterDtos(repo.findBySourceAndDestination(source,destination));
			
			 return travelResponseDto; // Directly return the list, whether it's empty or not
		}catch(Exception ex) {
			throw new TravelServiceException("Failed to delete travel with ID ",ex);
		}
		
		
	}
}









