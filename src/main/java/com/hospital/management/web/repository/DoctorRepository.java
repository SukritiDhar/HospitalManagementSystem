package com.hospital.management.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.web.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	Doctor findByDrId(Long drId);

	Doctor findByDrName(String drName);

}
