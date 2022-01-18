package com.hospital.management.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.web.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	List<Patient> findByVisitedDoctor(String visitedDoctor);
	
}