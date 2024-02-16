package com.atsmart.travelservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atsmart.travelservice.entity.Cab;


@Repository
public interface CabRepo extends JpaRepository<Cab,Integer> {


}
