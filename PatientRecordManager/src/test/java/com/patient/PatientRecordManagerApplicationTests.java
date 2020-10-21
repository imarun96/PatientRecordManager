package com.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.patient.controller.PatientController;
import com.patient.dao.PatientDao;
import com.patient.model.EmergencyContact;
import com.patient.model.PatientRecord;
import com.patient.service.PatientService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PatientRecordManagerApplicationTests {

	@Autowired
	PatientService patientService;

	@MockBean
	PatientDao patientDao;

	@Autowired
	PatientController patientController;

	private Calendar cal = Calendar.getInstance();

	@Test
	public void addUserTest() {
		EmergencyContact emergencyContact = new EmergencyContact("Bhuvana", "9629479234", "Sister");
		PatientRecord record = new PatientRecord(2, "Arun Balaji", "2020-10-10", "Male", "abc@gmail.com", "7010184671",
				"trichy", 1, "O-", emergencyContact, "admin", "admin", new Timestamp(cal.getTimeInMillis()),
				new Timestamp(cal.getTimeInMillis()));
		when(patientDao.insertPatientDetailsIntoDb(record)).thenReturn(record);
		assertEquals(record, patientService.insertPatientDetails(record));
		assertEquals(record, patientController.insertPatientRecord(record));
	}

	@Test
	public void getSinglePatient() {
		EmergencyContact emergencyContact = new EmergencyContact("Bhuvana", "9629479234", "Sister");
		PatientRecord record = new PatientRecord(2, "Arun Balaji", "2020-10-10", "Male", "abc@gmail.com", "7010184671",
				"trichy", 1, "O-", emergencyContact, "admin", "admin", new Timestamp(cal.getTimeInMillis()),
				new Timestamp(cal.getTimeInMillis()));
		when(patientDao.fetchSinglePatient(2L)).thenReturn(record);
		assertEquals(record, patientService.getSinglePatientRecord(2L));
		assertEquals(record, patientController.getSingleRecord(2L));
	}

	@Test
	public void getAllUserTest() {
		EmergencyContact emergencyContact = new EmergencyContact("Bhuvana", "9629479234", "Sister");
		when(patientDao.getAll())
				.thenReturn(Stream
						.of(new PatientRecord(2, "Arun Balaji", "2020-10-10", "Male", "abc@gmail.com", "7010184671",
								"trichy", 1, "O-", emergencyContact, "admin", "admin",
								new Timestamp(cal.getTimeInMillis()), new Timestamp(cal.getTimeInMillis())),
								new PatientRecord(2, "Arun Balaji", "2020-10-10", "Female", "abc@gmail.com",
										"7010184671", "trichy", 1, "O-", emergencyContact, "admin", "admin",
										new Timestamp(cal.getTimeInMillis()), new Timestamp(cal.getTimeInMillis())))
						.collect(Collectors.toList()));
		assertEquals(2, patientService.getAll().size());
		assertEquals(2, patientController.getAllPatientRecords().size());
	}

	@Test
	public void deleteUserTest() {
		EmergencyContact emergencyContact = new EmergencyContact("Bhuvana", "9629479234", "Sister");
		PatientRecord record = new PatientRecord(2, "Arun Balaji", "2020-10-10", "Male", "abc@gmail.com", "7010184671",
				"trichy", 1, "O-", emergencyContact, "admin", "admin", new Timestamp(cal.getTimeInMillis()),
				new Timestamp(cal.getTimeInMillis()));
		when(patientDao.insertPatientDetailsIntoDb(record)).thenReturn(record);
		patientService.deleteById(2L);
		verify(patientDao, times(1)).delete(2L);
	}
}