package com.api.v1.breakfast.Breakfast.dto;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

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

	@CPF
	@NotEmpty(message = "Mandatory Field")
	@Length(min = 11,  max = 11, message = "The length must be 11 characters")
	private String cpf;	

	private String status;
	
	private Set<ItemDTO> items = new HashSet<ItemDTO>();
	
}
