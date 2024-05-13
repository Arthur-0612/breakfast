package com.api.v1.breakfast.Breakfast.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

	private Long id;

	@NotEmpty(message = "Mandatory Field")
	@Length(min = 1, max = 210, message = "The length must be between 1 and 210 characters")
	private String name;

	
	@NotEmpty(message = "Mandatory Field")
	@Length(min = 11, max = 14, message = "The length must be between 1 and 14 characters")
	private String cpf;
	
	
}
