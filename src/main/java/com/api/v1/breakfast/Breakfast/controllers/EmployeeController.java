package com.api.v1.breakfast.Breakfast.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}