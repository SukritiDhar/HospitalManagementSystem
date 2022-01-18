package com.hospital.management.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor_information")
public class Doctor {
	@Id
	private Long drId;
	private String drName;
	private int age;
	private String gender;
	private String specialistField;
	public Doctor() {}
	public Doctor(Long drId, String drName, int age, String gender, String specialistField) {
		super();
		this.drId = drId;
		this.drName = drName;
		this.age = age;
		this.gender = gender;
		this.specialistField = specialistField;
	}
	public Long getDrId() {
		return drId;
	}
	public void setDrId(Long drId) {
		this.drId = drId;
	}
	public String getDrName() {
		return drName;
	}
	public void setDrName(String drName) {
		this.drName = drName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSpecialistField() {
		return specialistField;
	}
	public void setSpecialistField(String specialistField) {
		this.specialistField = specialistField;
	}
	@Override
	public String toString() {
		return "Doctor [drId=" + drId + ", drName=" + drName + ", age=" + age + ", gender=" + gender + ", specialistField="
				+ specialistField + "]";
	}
}
