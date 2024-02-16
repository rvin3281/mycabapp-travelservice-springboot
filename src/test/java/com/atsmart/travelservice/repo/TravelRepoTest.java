package com.atsmart.travelservice.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atsmart.travelservice.entity.Cab;
import com.atsmart.travelservice.entity.Driver;
import com.atsmart.travelservice.entity.Travel;

@SpringBootTest
public class TravelRepoTest {

	@Autowired
	private TravelRepo travelRepo;
	
	@Autowired
    private DriverRepo driverRepo; // Assuming you have this

    @Autowired
    private CabRepo cabRepo; // Assuming you have this
	
	Travel travel;
	Driver driver;
    Cab cab;
	
	@BeforeEach
	void setUp() {
		
		// Initialize Cab entity
        cab = new Cab();
        cab.setCabModel("Model X");
        cab.setCabName("Name X");
        cab.setCabPlateNum("Plate123");
        cab.setStatus(1);
        // Assuming the timestamps are handled automatically by @PrePersist
        cab = cabRepo.save(cab);
        
     // Initialize Driver entity
        driver = new Driver();
        driver.setDriverName("John Doe");
        driver.setDriverContact("123456789");
        driver.setDriverIdentificationNum("ID12345");
        driver.setStatus(1);
        driver.setCab(cab); // Set the previously saved Cab to this driver
        // Again, assuming timestamps are handled by @PrePersist
        driver = driverRepo.save(driver);
        
     // Create Travel entity with all required fields
        travel = new Travel();
        travel.setTravelSource("ipoh");
        travel.setTravelDestination("KL");
        travel.setTravel_time(LocalTime.of(10, 30)); // For example, 10:30 AM
        travel.setTravelDate(new Date(System.currentTimeMillis())); // Today's date
        travel.setTravelCost("100");
        travel.setStatus(1); // Assuming '1' indicates an active status
        travel.setDriver(driver);
        travel.setCab(cab);
        
        // Save the initialized travel object
        travel = travelRepo.save(travel);
        
	}
	
	@AfterEach
	void tearDown() {
		
		travelRepo.deleteAll();
		driverRepo.deleteAll();
		cabRepo.deleteAll();
		
		travel=null;
		driver=null;
		cab=null;

	}
	
	@Test
	void testFindBySourceAndDestination() {
		
		List<Travel> travelList = travelRepo.findBySourceAndDestination("ipoh", "KL");
		
		assertThat(travelList.get(0).getTravelSource()).isEqualTo(travel.getTravelSource());
		assertThat(travelList.get(0).getTravelDestination()).isEqualTo(travel.getTravelDestination());
	}
	
	@Test
	void testFindBySourceAndDestination_NotFound() {
		List<Travel> travelList = travelRepo.findBySourceAndDestination("johor", "ipoh");
		
		assertThat(travelList.isEmpty()).isFalse();
	}
	
}





















