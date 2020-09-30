package com.patient;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("spring")
public class Credential {
	private String userName;
	private String password;
}