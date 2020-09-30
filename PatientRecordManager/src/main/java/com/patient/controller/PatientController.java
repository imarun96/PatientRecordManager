package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.model.PatientRecord;
import com.patient.service.PatientService;

@RestController
public class PatientController {

	@Autowired
	PatientService patientService;

	@PostMapping("/addNewRecord")
	@Cacheable(value = "getRecordWith")
	public String insertPatientRecord(@RequestBody PatientRecord patientRecord) {
		patientService.insertPatientDetails(patientRecord);
		return "Record Inserted";
	}

	@GetMapping("/listAllPatient")
	@Cacheable(value = "getRecordWithDat")
	public List<PatientRecord> getAllPatientRecords() {
		return patientService.getAll();
	}

	@CacheEvict(value = "getRecordWithDat", allEntries = true)
	@GetMapping("/delete/{id}")
	public String deletePatientId(@PathVariable(name = "id") Long id) {
		return patientService.deleteById(id);
	}
}