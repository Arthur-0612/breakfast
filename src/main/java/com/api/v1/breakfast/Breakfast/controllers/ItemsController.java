package com.api.v1.breakfast.Breakfast.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.v1.breakfast.Breakfast.dto.ItemsDTO;
import com.api.v1.breakfast.Breakfast.models.Items;
import com.api.v1.breakfast.Breakfast.services.ItemsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/items")
@RequiredArgsConstructor
public class ItemsController {

	private final ItemsService service;

	@GetMapping("/{id}")
	public ResponseEntity<ItemsDTO> findById(@PathVariable Long id) {
		var itemsDb = service.findById(id);

		return ResponseEntity.status(HttpStatus.OK).body(service.toDto(itemsDb));
	}

	@GetMapping("/all")
	public ResponseEntity<List<ItemsDTO>> findAll() {
		List<Items> itemsDb = service.findAll();
		List<ItemsDTO> itemsDto = itemsDb.stream().map(service::toDto).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(itemsDto);
	}

	@PostMapping("/save")
	public ResponseEntity<ItemsDTO> save(@RequestBody @Valid ItemsDTO dto) {
		
		var itemsDb = service.save(dto);
				
		return ResponseEntity.status(HttpStatus.CREATED).body(service.toDto(itemsDb));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ItemsDTO> update(@PathVariable Long id, @RequestBody @Valid ItemsDTO dto) {
		
		var itemsDb = service.update(id, dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(service.toDto(itemsDb));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	 
}
