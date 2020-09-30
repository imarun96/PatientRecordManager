package com.patient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.dao.PatientDao;
import com.patient.model.PatientRecord;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientDao patientDao;

	@Override
	public void insertPatientDetails(PatientRecord details) {
		patientDao.insertPatientDetailsIntoDb(details);
	}

	@Override
	public List<PatientRecord> getAll() {
		List<PatientRecord> patientList = patientDao.getAll();
		return patientList;
	}

	@Override
	public String deleteById(Long id) {
		return patientDao.delete(id);
	}
}