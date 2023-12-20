package com.nsd.race.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsd.race.entities.Cars;

@Repository
public interface CarRepository extends JpaRepository<Cars, Integer>{

}
