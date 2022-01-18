package com.hospital.management.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import com.hospital.management.web.entity.Doctor;
import com.hospital.management.web.repository.DoctorRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class DoctorTest {

	@MockBean
	private DoctorRepository doctorRepository;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateDoctor() {
	    Doctor savedDoctor = doctorRepository.save(new Doctor(1L,"Sukriti Dhar", 21, "Female", "Diabetis Specialist"));
	    assertThat(savedDoctor);
	}
	
	@Test
	@Order(2)
	public void testGetAllDoctors() {
	    List<Doctor> doctors = (List<Doctor>) doctorRepository.findAll();
	    assertNotNull(doctors);
	}
	
	@Test
	@Order(3)
	public void testGetDoctorById() {
	    Optional<Doctor> doctors = doctorRepository.findById(1L);
	    assertNotNull(doctors);
	}
	
	@Test
	@Order(4)
	public void testGetDoctorByName() {
		String name= "Sukriti Dhar";
		Optional<Doctor> doctors = Optional.ofNullable((doctorRepository.findByDrName(name)));
		doctors.ifPresent(existingDoctor -> {
			assertThat(existingDoctor.getDrName()).isEqualTo(name);
		});
	}
	
	@Test
	@Order(5)
	public void testUpdateDoctor() {
		String update= "All";
		doctorRepository.save(new Doctor(1L,"Sukriti Dhar", 21, "Female", update));
		Optional<Doctor> updatedDoctor= doctorRepository.findById(1L);
		updatedDoctor.ifPresent(existingDoctor -> {
			assertThat(existingDoctor.getSpecialistField()).isEqualTo(update);
		});
	}
	
	@Test
	@Rollback(false)
	@Order(6)
	public void testDeleteDoctor() {
		Long id= 1L;
		doctorRepository.deleteById(id);
		Boolean isExistAfterDelete= doctorRepository.findById(id).isPresent();
		Assertions.assertFalse(isExistAfterDelete);
	}
	
}
