package com.api.v1.breakfast.Breakfast.services;

import java.util.List;

import com.api.v1.breakfast.Breakfast.dto.BreakfastDTO;
import com.api.v1.breakfast.Breakfast.models.Breakfast;

public interface BreakfastService {

	Breakfast findById(Long id);

	List<Breakfast> findAll();

	Breakfast save(BreakfastDTO dto);

	Breakfast update(Long id, BreakfastDTO dto);

	void delete(Long id);

	BreakfastDTO toDto(Breakfast entity);

	Breakfast toEntity(BreakfastDTO dto);
	
}
