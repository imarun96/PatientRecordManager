package com.patient.service;

import java.util.Calendar;
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
	public PatientRecord insertPatientDetails(PatientRecord details) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		details.setAge(year-Integer.valueOf(details.getDob().split("-")[0]));
		return patientDao.insertPatientDetailsIntoDb(details);
	}

	@Override
	public List<PatientRecord> getAll() {
		return patientDao.getAll();
	}

	@Override
	public String deleteById(Long id) {
		return patientDao.delete(id);
	}

	@Override
	public PatientRecord getSinglePatientRecord(Long id) {
		return patientDao.fetchSinglePatient(id);
	}
}