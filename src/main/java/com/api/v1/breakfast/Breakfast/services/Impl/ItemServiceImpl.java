package com.api.v1.breakfast.Breakfast.services.Impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.v1.breakfast.Breakfast.Exceptions.GlobalException;
import com.api.v1.breakfast.Breakfast.dto.ItemDTO;
import com.api.v1.breakfast.Breakfast.models.Item;
import com.api.v1.breakfast.Breakfast.repositories.ItemRepository;
import com.api.v1.breakfast.Breakfast.services.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository repository;

	@Override
	public Item findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new GlobalException("Item not Found", HttpStatus.BAD_GATEWAY, 100));
	}

	@Override
	public List<Item> findAll() {
		return repository.findAll();
	}

	@Override
	public Item save(ItemDTO dto) {
		validateDescription(dto.getDescription());
		var itemDb = toEntity(dto);
		
		return repository.save(itemDb);
	}
	
	private void validateDescription(String description) {
		var item = repository.findByDescription(description);

		if (item.isPresent()) {
			throw new GlobalException("This description already exist", HttpStatus.BAD_REQUEST, 300);
		}
	}

	@Override
	public ItemDTO toDto(Item entity) {
		var dto = new ItemDTO();
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());

		return dto;
	}

	@Override
	public Item toEntity(ItemDTO dto) {
		var entity = new Item();
		entity.setId(dto.getId());
		entity.setDescription(dto.getDescription());
		return entity;
	}

}
