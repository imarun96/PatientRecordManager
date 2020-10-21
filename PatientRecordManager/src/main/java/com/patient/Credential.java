package com.patient;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("spring")
public class Credential {
	/* User Name for Hashicorp Vault */
	private String USERNAME;
	/* Password for Hashicorp Vault */
	private String PASSWORD;
}