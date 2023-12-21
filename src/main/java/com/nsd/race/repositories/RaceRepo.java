package com.nsd.race.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsd.race.entities.Race;

public interface RaceRepo extends JpaRepository<Race, Integer> {

}
