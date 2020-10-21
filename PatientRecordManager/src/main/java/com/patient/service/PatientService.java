package com.patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patient.model.PatientRecord;

@Service
public interface PatientService {
	public PatientRecord insertPatientDetails(PatientRecord details);

	public List<PatientRecord> getAll();

	public String deleteById(Long id);

	public PatientRecord getSinglePatientRecord(Long id);
}