package com.atsmart.travelservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atsmart.travelservice.entity.Travel;

@Repository
public interface TravelRepo extends JpaRepository<Travel, Integer> {

	String sql1 = "select t from Travel t where t.travelSource=:source AND t.travelDestination = :destination";
	@Query(sql1)
	List<Travel> findBySourceAndDestination(@Param("source") String source, 
            @Param("destination") String destination);
	
}
