package com.api.v1.breakfast.Breakfast.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.v1.breakfast.Breakfast.dto.EmployeeDTO;
import com.api.v1.breakfast.Breakfast.models.Employee;
import com.api.v1.breakfast.Breakfast.services.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService service;

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id) {
		var employeeDb = service.findById(id);

		return ResponseEntity.status(HttpStatus.OK).body(service.toDto(employeeDb));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeDTO>> findAll() {
		List<Employee> employeeDb = service.findAll();
		List<EmployeeDTO> employeeDto = employeeDb.stream()
												.map(service::toDto)
												.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<EmployeeDTO>> findByStatus(@PathVariable String status) {
		var employeeDb = service.findByStatus(status);
		return ResponseEntity.status(HttpStatus.OK).body(employeeDb);
	}

	@PostMapping("/save")
	public ResponseEntity<EmployeeDTO> save(@RequestBody @Valid EmployeeDTO dto) {

		var employeeDb = service.save(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(service.toDto(employeeDb));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeDTO> update(@PathVariable Long id, @RequestBody @Valid EmployeeDTO dto) {

		var employeeDb = service.update(id, dto);

		return ResponseEntity.status(HttpStatus.OK).body(service.toDto(employeeDb));
	}
	
	@PatchMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody @Valid EmployeeDTO dto) {
		service.delete(id, dto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
