package com.api.v1.breakfast.Breakfast.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreakfastDTO {

	private Long id;

	private LocalDate dateBreakfast;
	
	private String description;

	private List<EmployeeDTO> employees;

}