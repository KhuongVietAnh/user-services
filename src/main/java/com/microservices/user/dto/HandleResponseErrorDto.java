package com.microservices.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandleResponseErrorDto {

	private String status;

	private String message;

	private String fields;
}
