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

import com.hospital.management.web.entity.Patient;
import com.hospital.management.web.repository.PatientRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class PatientTest {

	@MockBean
	private PatientRepository patientRepository;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreatePatient() {
	    Patient savedPatient = patientRepository.save(new Patient(1L,"Akash Ganguly", "Sukriti Dhar", "15/01/2022"));
	    assertThat(savedPatient);
	}
	
	@Test
	@Order(2)
	public void testGetAllPatients() {
	    List<Patient> patients = (List<Patient>) patientRepository.findAll();
	    assertNotNull(patients);
	}
	
	@Test
	@Order(3)
	public void testGetPatientById() {
	    Optional<Patient> patients = patientRepository.findById(1L);
	    assertNotNull(patients);
	}
	
	@Test
	@Order(4)
	public void testUpdatePatient() {
		String update= "Akash";
		patientRepository.save(new Patient(1L, update, "Sukriti Dhar", "15/01/2022"));
		Optional<Patient> updatedPatient= patientRepository.findById(1L);
		updatedPatient.ifPresent(existingPatient -> {
			assertThat(existingPatient.getPtName()).isEqualTo(update);
		});
	}
	
	@Test
	@Rollback(false)
	@Order(5)
	public void testDeletePatient() {
		Long id= 1L;
		patientRepository.deleteById(id);
		Boolean isExistAfterDelete= patientRepository.findById(id).isPresent();
		Assertions.assertFalse(isExistAfterDelete);
	}
	
}
