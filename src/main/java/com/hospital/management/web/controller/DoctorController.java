package com.hospital.management.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.hospital.management.web.entity.Doctor;
import com.hospital.management.web.exception.ResourceNotFoundException;
import com.hospital.management.web.repository.DoctorRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class DoctorController {
	@Autowired
	private DoctorRepository doctorRepository;
	
	@PostMapping("/doctors")
	public Doctor createDoctor(@RequestBody Doctor doctor)
	{
		return doctorRepository.save(doctor);
	}
	
	@GetMapping("/doctors")
	public List<Doctor> getAllDoctors()
	{
		return doctorRepository.findAll();
	}
	
	@GetMapping("/doctors/{drId}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Long drId)
	{
		Doctor doctor= doctorRepository.findById(drId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor does not exist with id:" + drId));
		return ResponseEntity.ok(doctor);
	}
	
	@GetMapping("/doctors/show/{drName}")
	public ResponseEntity<Doctor> getDoctorByName(@PathVariable String drName)
	{
		Doctor doctor= doctorRepository.findByDrName(drName);
		return ResponseEntity.ok(doctor);
	}
	
	@PutMapping("/doctors/{drId}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable Long drId, @RequestBody Doctor doctorDetails)
	{
		Doctor doctor= doctorRepository.findById(drId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor does not exist with id:" + drId));
		doctor.setDrId(doctorDetails.getDrId());
		doctor.setDrName(doctorDetails.getDrName());
		doctor.setAge(doctorDetails.getAge());
		doctor.setGender(doctorDetails.getGender());
		doctor.setSpecialistField(doctorDetails.getSpecialistField());
		Doctor updatedDoctor= doctorRepository.save(doctor);
		return ResponseEntity.ok(updatedDoctor);
	}
	
	@DeleteMapping("/doctors/{drId}")
	public ResponseEntity<Map<String, Boolean>> deleteDoctor(@PathVariable Long drId)
	{
		Doctor doctor= doctorRepository.findById(drId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor does not exist with id:" + drId));
		doctorRepository.delete(doctor);
		Map<String, Boolean> response= new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
