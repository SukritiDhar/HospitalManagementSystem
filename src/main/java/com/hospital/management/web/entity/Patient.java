package com.hospital.management.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "patient_information")
public class Patient {
	@Id
	private Long ptId;
	private String ptName;
	private String visitedDoctor;
	private String dateOfVisit;
	public Patient() {}
	public Patient(Long ptId, String ptName, String visitedDoctor, String dateOfVisit) {
		super();
		this.ptId = ptId;
		this.ptName = ptName;
		this.visitedDoctor = visitedDoctor;
		this.dateOfVisit = dateOfVisit;
	}
	public Long getPtId() {
		return ptId;
	}
	public void setPtId(Long ptId) {
		this.ptId = ptId;
	}
	public String getPtName() {
		return ptName;
	}
	public void setPtName(String ptName) {
		this.ptName = ptName;
	}
	public String getVisitedDoctor() {
		return visitedDoctor;
	}
	public void setVisitedDoctor(String visitedDoctor) {
		this.visitedDoctor = visitedDoctor;
	}
	public String getDateOfVisit() {
		return dateOfVisit;
	}
	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}
	@Override
	public String toString() {
		return "Patient [ptId=" + ptId + ", ptName=" + ptName + ", visitedDoctor=" + visitedDoctor + ", dateOfVisit="
				+ dateOfVisit + "]";
	}
}
