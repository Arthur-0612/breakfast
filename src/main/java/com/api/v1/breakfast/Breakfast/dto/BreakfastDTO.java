package com.api.v1.breakfast.Breakfast.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreakfastDTO {

	private Long id;

	private LocalDate dateBreakfast;
	
	private String descricao;

	private Set<EmployeeDTO> employee = new HashSet<>();

}