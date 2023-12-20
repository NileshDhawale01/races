package com.nsd.race.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsd.race.entities.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer>{

}
