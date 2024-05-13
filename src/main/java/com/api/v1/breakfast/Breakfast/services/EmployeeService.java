package com.api.v1.breakfast.Breakfast.services;

import com.api.v1.breakfast.Breakfast.dto.EmployeeDTO;
import com.api.v1.breakfast.Breakfast.models.Employee;

public interface EmployeeService {

	
	Employee findById(Long id);
	
	Employee save(EmployeeDTO dto);
	
	Employee update(Long id, EmployeeDTO dto);
	
	EmployeeDTO toDto(Employee entity);
	
	Employee toEntity(EmployeeDTO dto);
}
