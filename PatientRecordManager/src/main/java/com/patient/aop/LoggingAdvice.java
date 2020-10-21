package com.patient.aop;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.model.LoggingSaver;
import com.patient.repository.LoggingSaverRepository;

@Aspect
@Component
public class LoggingAdvice {

	@Autowired
	LoggingSaverRepository repository;

	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

	@Pointcut("execution(* com.patient.controller.*.*(..) ) || execution(* com.patient.dao.*.*(..) )")
	public void myPointcut() {
	}

	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();
		String logStatement = "Method [" + methodName + "] invoked from the class [" + className
				+ "] with the input Param - [" + mapper.writeValueAsString(array) + "] at " + now.format(formater)
				+ " (UTC)";
		log.info("Method [{}] invoked from the class [{}] with the Input Param - [{}] at {} (UTC)", methodName,
				className, mapper.writeValueAsString(array), now.format(formater));
		LoggingSaver loggingSaver = new LoggingSaver();
		loggingSaver.setMethodName(methodName);
		loggingSaver.setClassName(className);
		loggingSaver.setDateTime(now.format(formater));
		loggingSaver.setLogStatement(logStatement);
		//repository.addLog(loggingSaver);
		Object object = pjp.proceed();
		logStatement = "Method [" + methodName + "] invoked from the class [" + className + "] with the Response - ["
				+ mapper.writeValueAsString(object) + "] at " + now.format(formater) + " (UTC)";
		log.info("Method [{}] invoked from the class [{}] with the Response - [{}] at {} (UTC)", methodName, className,
				mapper.writeValueAsString(object), now.format(formater));
		LoggingSaver loggingObj = new LoggingSaver();
		loggingObj.setMethodName(methodName);
		loggingObj.setClassName(className);
		loggingObj.setDateTime(now.format(formater));
		loggingObj.setLogStatement(logStatement);
		//repository.addLog(loggingObj);
		return object;
	}
}