package com.api.v1.breakfast.Breakfast.services;

import java.util.List;

import com.api.v1.breakfast.Breakfast.dto.EmployeeDTO;
import com.api.v1.breakfast.Breakfast.models.Employee;

public interface EmployeeService {

	Employee findById(Long id);

	List<Employee> findAll();

	Employee save(EmployeeDTO dto);

	Employee update(Long id, EmployeeDTO dto);

	void delete(Long id);

	EmployeeDTO toDto(Employee entity);

	Employee toEntity(EmployeeDTO dto);
}
