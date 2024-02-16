package com.atsmart.travelservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atsmart.travelservice.entity.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

	String sql1 = "select d from Driver d where d.cab.cabId=:id";
	@Query(sql1)
	public Optional<Driver>  checkCabOnDriver (@Param("id") int id);

}
