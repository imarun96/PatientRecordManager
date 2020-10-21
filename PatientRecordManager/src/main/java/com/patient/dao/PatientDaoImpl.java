package com.patient.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;

import com.patient.Credential;
import com.patient.model.PatientRecord;

@Repository
@EnableConfigurationProperties(Credential.class)
public class PatientDaoImpl implements PatientDao {

	private static Credential credential;

	public PatientDaoImpl(Credential credential) {
		PatientDaoImpl.credential = credential;
	}

	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory;
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(PatientRecord.class);
		configuration.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/patient");
		configuration.setProperty("hibernate.connection.username", credential.getUSERNAME());
		configuration.setProperty("hibernate.connection.password", credential.getPASSWORD());
		configuration.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("show_sql", "true");
		configuration.setProperty("hibernate.connection.pool_size", "10");
		ServiceRegistry builder = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(builder);
		return sessionFactory;
	}

	@Override
	public PatientRecord insertPatientDetailsIntoDb(PatientRecord details) {
		SessionFactory sessionactory = PatientDaoImpl.getSessionFactory();
		Session session = sessionactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Calendar cal = Calendar.getInstance();
		details.setInputDateTime(new Timestamp(cal.getTimeInMillis()));
		details.setLastUpdateDateTime(new Timestamp(cal.getTimeInMillis()));
		session.save(details);
		session.getTransaction().commit();
		session.close();
		return details;
	}

	@Override
	public List<PatientRecord> getAll() {
		SessionFactory sessionactory = PatientDaoImpl.getSessionFactory();
		Session session = sessionactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		String hql = "FROM patient";
		Query q = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<PatientRecord> patientRecord = q.list();
		session.getTransaction().commit();
		session.close();
		return patientRecord;
	}

	@Override
	public String delete(Long id) {
		SessionFactory sessionactory = PatientDaoImpl.getSessionFactory();
		Session session = sessionactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		PatientRecord patientRecord = (PatientRecord) session.get(PatientRecord.class, id);
		session.delete(patientRecord);
		session.getTransaction().commit();
		session.close();
		return String.valueOf("Deleted");
	}

	@Override
	public PatientRecord fetchSinglePatient(Long id) {
		SessionFactory sessionactory = PatientDaoImpl.getSessionFactory();
		Session session = sessionactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		PatientRecord patientRecord = (PatientRecord) session.get(PatientRecord.class, id);
		session.getTransaction().commit();
		session.close();
		return patientRecord;
	}
}