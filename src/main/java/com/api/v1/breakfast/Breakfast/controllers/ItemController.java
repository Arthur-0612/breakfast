package com.api.v1.breakfast.Breakfast.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.v1.breakfast.Breakfast.dto.EmployeeDTO;
import com.api.v1.breakfast.Breakfast.dto.ItemDTO;
import com.api.v1.breakfast.Breakfast.models.Employee;
import com.api.v1.breakfast.Breakfast.models.Item;
import com.api.v1.breakfast.Breakfast.services.ItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemDTO> findById(@PathVariable Long id) {
		var itemDb = service.findById(id);

		return ResponseEntity.status(HttpStatus.OK).body(service.toDto(itemDb));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ItemDTO>> findAll() {
		List<Item> itemDb = service.findAll();
		List<ItemDTO> itemDto = itemDb.stream()
				.map(service::toDto)
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(itemDto);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ItemDTO> save(@RequestBody @Valid ItemDTO dto) {
		var itemDb = service.save(dto);
		return ResponseEntity.status(HttpStatus.OK).body(service.toDto(itemDb));
	}

}
