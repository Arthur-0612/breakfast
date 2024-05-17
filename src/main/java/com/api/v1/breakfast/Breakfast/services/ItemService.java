package com.api.v1.breakfast.Breakfast.services;

import java.util.List;

import com.api.v1.breakfast.Breakfast.dto.ItemDTO;
import com.api.v1.breakfast.Breakfast.models.Item;

public interface ItemService {
	
	Item findById(Long id);

	List<Item> findAll();

	Item save(ItemDTO dto);
	
	ItemDTO toDto(Item entity);

	Item toEntity(ItemDTO dto);
}
