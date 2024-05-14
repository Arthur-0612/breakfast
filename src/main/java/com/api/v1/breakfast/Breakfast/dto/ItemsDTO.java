package com.api.v1.breakfast.Breakfast.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemsDTO {

	private Long id;

	@NotEmpty(message = "Mandatory Field")
	@Length(min = 1, max = 80, message = "The length must be between 1 and 80 characters")
	private String name;
}
