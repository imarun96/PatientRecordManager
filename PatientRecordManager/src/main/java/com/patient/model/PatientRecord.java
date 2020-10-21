package com.patient.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "patient")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	@ApiModelProperty(value = "Auto-Generated value. No need to enter.")
	private long patient_id;
	@Column(name = "name")
	@ApiModelProperty(value = "Patient Name")
	private String name;
	@Column(name = "dob")
	@ApiModelProperty(value = "Date of Birth")
	private String dob;
	@Column(name = "sex")
	@ApiModelProperty(value = "Sex")
	private String sex;
	@Column(name = "email")
	@ApiModelProperty(value = "Patient Email")
	private String email;
	@Column(name = "phoneNumber")
	@ApiModelProperty(value = "Patient Phone Number")
	private String phnNumber;
	@Column(name = "address")
	@ApiModelProperty(value = "Patient Address")
	private String address;
	@Column(name = "age")
	@ApiModelProperty(value = "Patient Age")
	private int age;
	@Column(name = "bloodGroup")
	@ApiModelProperty(value = "Patient Blood Group")
	private String bloodGroup;

	@Column(name = "emergencyContact")
	@ApiModelProperty(value = "Emergency Contact of Patient")
	private EmergencyContact emergencyContact;

	@Column(name = "inputUserId")
	private String inputUserId;
	@Column(name = "lastUpdateUserId")
	private String lastUpdateUserId;
	@Column(name = "inputDateTime")
	private Timestamp inputDateTime;
	@Column(name = "lastUpdateDateTime")
	private Timestamp lastUpdateDateTime;
}