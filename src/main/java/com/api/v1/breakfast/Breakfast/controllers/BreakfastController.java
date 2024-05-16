package com.api.v1.breakfast.Breakfast.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.v1.breakfast.Breakfast.dto.BreakfastDTO;
import com.api.v1.breakfast.Breakfast.models.Breakfast;
import com.api.v1.breakfast.Breakfast.services.BreakfastService;
import com.api.v1.breakfast.Breakfast.services.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/breakfast")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BreakfastController {
	
	private final BreakfastService service;
	
	@PostMapping("/save")
	public ResponseEntity<BreakfastDTO> save (@RequestBody @Valid BreakfastDTO dto) {
		var breakfast = service.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.toDto(breakfast));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Breakfast>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
}
