package com.patient.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Endpoint(id = "custom-endpoint", enableByDefault = true)
public class CustomEndPoint {
	@ReadOperation
	public EndPointResponse features() {
		return new EndPointResponse(12345, "Custom Endpoint", "Active");
	}
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class EndPointResponse {
	private int id;
	private String name;
	private String status;
}