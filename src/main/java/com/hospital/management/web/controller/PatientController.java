package com.hospital.management.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.management.web.entity.Count;
import com.hospital.management.web.entity.Patient;
import com.hospital.management.web.exception.ResourceNotFoundException;
import com.hospital.management.web.repository.PatientRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
	
	@PostMapping("/patients")
	public Patient createPatient(@RequestBody Patient patient)
	{
		return patientRepository.save(patient);
	}
	
	@GetMapping("/patients")
	public List<Patient> getAllPatients()
	{
		return patientRepository.findAll();
	}
	
	@GetMapping("/patients/{ptId}")
	public Optional<Patient> getPatientById(@PathVariable Long ptId)
	{
		return patientRepository.findById(ptId);
	}
	
	@RequestMapping("/patients/count/{visitedDoctor}")
	public Count getCount(@PathVariable String visitedDoctor)
	{
		List<Patient> pt= patientRepository.findByVisitedDoctor(visitedDoctor);
		Count count = new Count();
		count.setCount(pt.size());
		return count;
	}
	
	@PutMapping("/patients/{ptId}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long ptId, @RequestBody Patient patientDetails)
	{
		Patient patient= patientRepository.findById(ptId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient does not exist with id:" + ptId));
		patient.setPtId(patientDetails.getPtId());
		patient.setPtName(patientDetails.getPtName());
		patient.setVisitedDoctor(patientDetails.getVisitedDoctor());
		patient.setDateOfVisit(patientDetails.getDateOfVisit());
		Patient updatedPatient= patientRepository.save(patient);
		return ResponseEntity.ok(updatedPatient);
	}
	
	@DeleteMapping("/patients/{ptId}")
	public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable Long ptId)
	{
		Patient patient= patientRepository.findById(ptId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient does not exist with id:" + ptId));
		patientRepository.delete(patient);
		Map<String, Boolean> response= new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
