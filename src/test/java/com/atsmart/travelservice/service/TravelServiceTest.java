package com.atsmart.travelservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.atsmart.travelservice.dto.CabDto;
import com.atsmart.travelservice.dto.Travel.TravelRegisterDto;
import com.atsmart.travelservice.dto.Travel.TravelResponseDto;
import com.atsmart.travelservice.dto.Travel.TravelUpdateDto;
import com.atsmart.travelservice.dto.driver.DriverResponseDto;
import com.atsmart.travelservice.entity.Cab;
import com.atsmart.travelservice.entity.Driver;
import com.atsmart.travelservice.entity.Travel;
import com.atsmart.travelservice.mapper.TravelMapper;
import com.atsmart.travelservice.repo.CabRepo;
import com.atsmart.travelservice.repo.DriverRepo;
import com.atsmart.travelservice.repo.TravelRepo;

class TravelServiceTest {
	
	@Mock
	TravelRepo repo;
	
	@Mock
	DriverRepo driverRepo;
	
	@Mock
	CabRepo cabRepo;
	
	@Mock
	TravelMapper mapper;
	
	@InjectMocks
    private TravelService travelService; // Ask Mockito to inject the mock above into CloudVendorService
	
	AutoCloseable autoCloseable;
	
	TravelRegisterDto dto;
	Driver driver;
	Cab cab;
	Travel travel;
	CabDto cabDto;
	DriverResponseDto driverResponseDto;
	TravelUpdateDto updateDto;
	TravelResponseDto expectedResponse;
	
	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		
		 // Given
        dto = new TravelRegisterDto("Source", "Destination", "10:30", "100", new Date(System.currentTimeMillis()), 1, 1);
        
        cab = new Cab(1, "Cab Model", "Cab Name", "Cab PlateNum", null, null, 1, null);
        driver = new Driver(1, "Driver Name", "Driver Contact", "Driver IdentificationNum", null, null, 1, cab, null);
        
        
        travel = new Travel(1, "Source", "Destination", LocalTime.of(10, 30), dto.getTravelDate(), "100", null, null, 1, driver, cab);
        
        cabDto = new CabDto(1, "Cab Model", "Cab Name", "Cab PlateNum");
        driverResponseDto = new DriverResponseDto(1, "Driver Name", "Driver Contact", "Driver IdentificationNum",cabDto);
        updateDto = new TravelUpdateDto(1, "New Source", "New Destination", LocalTime.now(), new Date(System.currentTimeMillis()), "200", 1, 1);
        expectedResponse = new TravelResponseDto(1, "Source", "Destination", "10:30", dto.getTravelDate(), "100", driverResponseDto, cabDto);
        
        

	}
	
	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}
	
	@Test
	void testInsertTravel() {
		
		mock(TravelRegisterDto.class);
		
		int driverId = dto.getDriverId().intValue();
		when(driverRepo.findById(driverId)).thenReturn(Optional.of(driver));
		
		int cabId = dto.getCabId().intValue();
		when(cabRepo.findById(cabId)).thenReturn(Optional.of(cab));
		
		when(mapper.travelRegisterDtoToEntity(dto)).thenReturn(travel);
		
		when(repo.save(any(Travel.class))).thenReturn(travel);
		
		when(mapper.entityToTravelResponseDto(travel)).thenReturn(expectedResponse);
		
		assertThat(travelService.insertTravel(dto)).isEqualTo(expectedResponse);
	}

	@Test
	void testGetAllTravel() {
		
		when(repo.findAll()).thenReturn(List.of(travel));
		when(mapper.entitiesToTravelRegisterDtos(List.of(travel))).thenReturn(List.of(expectedResponse));
		
		assertThat(travelService.getAllTravel()).isEqualTo(List.of(expectedResponse));
		
	}

	@Test
	void testGetTravelById() {
		
		when(repo.findById(1)).thenReturn(Optional.of(travel));
		when(mapper.entityToTravelResponseDto(travel)).thenReturn(expectedResponse);
		
		assertThat(travelService.getTravelById(1)).isEqualTo(expectedResponse);
	}

	@Test
	void testUpdateTravel() {
		
		when(repo.findById(1)).thenReturn(Optional.of(travel));
		when(driverRepo.findById(updateDto.getDriverId().intValue())).thenReturn(Optional.of(driver));
		when(cabRepo.findById(updateDto.getDriverId().intValue())).thenReturn(Optional.of(cab));
		
		assertThat(travelService.updateTravel(updateDto, 1)).isTrue();
		verify(repo).save(travel);
		
	}


	@Test
	void testCheckAvailable() {
		
		when(repo.findBySourceAndDestination("Source", "Destination")).thenReturn(List.of(travel));
		when(mapper.entitiesToTravelRegisterDtos(List.of(travel))).thenReturn(List.of(expectedResponse));
		
		assertThat(travelService.checkAvailable("Source", "Destination")).isEqualTo(List.of(expectedResponse));
		
	}
}
