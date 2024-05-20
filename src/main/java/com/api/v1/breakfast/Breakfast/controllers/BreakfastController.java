package com.api.v1.breakfast.Breakfast.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.v1.breakfast.Breakfast.dto.BreakfastDTO;
import com.api.v1.breakfast.Breakfast.models.Breakfast;
import com.api.v1.breakfast.Breakfast.services.BreakfastService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/breakfast")
@RequiredArgsConstructor
public class BreakfastController {

    private final BreakfastService service;

    @GetMapping("/all")
    public ResponseEntity<List<BreakfastDTO>> findAll() {
        List<BreakfastDTO> breakfastDto = service.findAll();
        
        return ResponseEntity.status(HttpStatus.OK).body(breakfastDto);
    }

    @PostMapping("/save")
    public ResponseEntity<BreakfastDTO> save(@RequestBody BreakfastDTO dto) {
        Breakfast breakfast = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.toDto(breakfast));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<BreakfastDTO> update(@PathVariable Long id, @RequestBody @Valid BreakfastDTO dto) {
        var updatedBreakfast = service.update(id, dto);
        return ResponseEntity.ok(service.toDto(updatedBreakfast));
    }
}
