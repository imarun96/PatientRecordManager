package com.patient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.patient.model.PatientRecord;
import com.patient.service.PatientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/patients")
@Api(value = "Patient Manager Service", description = "Patient Module")
@CrossOrigin("*")
public class PatientController {

	private RabbitTemplate template;
	private PatientService patientService;

	public PatientController(RabbitTemplate template, PatientService patientService) {
		this.template = template;
		this.patientService = patientService;
	}
	/* RabbitTemplate for publishing message to RabbitMQ */

	/* KafkaTemplate to publish message */
	// @Autowired
	// private KafkaTemplate<String, Object> kafkaTemplate;
	/* Patient Service */
	/* Person Repository for DynamoDB */
	/* Logger Object */
	Logger logger = LoggerFactory.getLogger(PatientController.class);

	/*
	 * Param - PatientRecord To create a new Patient entry in Database
	 */

	@RequestMapping(path = "/patient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "To create new Patient Entry into Database")
	public PatientRecord insertPatientRecord(@RequestBody PatientRecord patientRecord) {
		PatientRecord patient = patientService.insertPatientDetails(patientRecord);
		logger.info("addNewRecord {}", patient);
		// kafkaTemplate.send("kafkaExample", patient);
		//template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, patient);
		return patient;
	}

	/*
	 * Lists all the Patient entry in DB
	 */

	@GetMapping("/patient")
	@ApiOperation(value = "To list Patient Directory")
	// @Cacheable(value = "getRecordWithDat", key = "#root.methodName")
	public List<PatientRecord> getAllPatientRecords() {
		logger.info("listAllPatient {}", patientService.getAll());
		// kafkaTemplate.send("kafkaExample",
		// patientService.getAll().stream().findAny());
		return patientService.getAll();
	}

	@GetMapping("/patient/{id}")
	@ApiOperation(value = "To list Patient Directory")
	// @Cacheable(value = "getRecordWithDat", key = "#root.methodName")
	public PatientRecord getSingleRecord(@PathVariable(name = "id") Long id) {
		// kafkaTemplate.send("kafkaExample",
		// patientService.getAll().stream().findAny());
		return patientService.getSinglePatientRecord(id);
	}

	/*
	 * Param - Patient ID Deletes a Patient entry
	 */

	@CacheEvict(value = "getRecordWithDat", allEntries = true)
	@DeleteMapping("/patient/{id}")
	@ApiOperation(value = "To delete existing Patient Entry in Database")
	public String deletePatientId(@PathVariable(name = "id") Long id) {
		logger.info("delete {}", "Deleted");
		return patientService.deleteById(id);
	}
}