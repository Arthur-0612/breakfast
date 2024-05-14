package com.api.v1.breakfast.Breakfast.services;

import java.util.List;

import com.api.v1.breakfast.Breakfast.dto.ItemsDTO;
import com.api.v1.breakfast.Breakfast.models.Items;

public interface ItemsService {

	
	Items findById(Long id);
	
	List<Items> findAll();
	
	Items save(ItemsDTO dto);
	
	Items update(Long id, ItemsDTO dto);
	
	void delete(Long id);
	
	ItemsDTO toDto(Items entity);
	
	Items toEntity(ItemsDTO dto);
}
