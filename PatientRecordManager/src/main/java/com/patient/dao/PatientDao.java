package com.patient.dao;

import java.util.List;

import com.patient.model.PatientRecord;

public interface PatientDao {
	public void insertPatientDetailsIntoDb(PatientRecord details);

	public List<PatientRecord> getAll();

	public String delete(Long id);
}