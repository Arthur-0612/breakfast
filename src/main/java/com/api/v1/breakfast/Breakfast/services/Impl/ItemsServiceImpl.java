package com.api.v1.breakfast.Breakfast.services.Impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.v1.breakfast.Breakfast.Exceptions.GlobalException;
import com.api.v1.breakfast.Breakfast.dto.ItemsDTO;
import com.api.v1.breakfast.Breakfast.models.Items;
import com.api.v1.breakfast.Breakfast.repositories.ItemsRepository;
import com.api.v1.breakfast.Breakfast.services.ItemsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemsServiceImpl implements ItemsService {

	private final ItemsRepository repository;

	@Override
	public Items findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new GlobalException("Item not found", HttpStatus.BAD_REQUEST, 100));
	}

	@Override
	public List<Items> findAll() {
		return repository.findAll();
	}

	@Override
	public Items save(ItemsDTO dto) {
		var items = repository.findByName(dto.getName());
		if(items.isPresent()) {
			throw new GlobalException("Name is present", HttpStatus.BAD_REQUEST, 200);
		}
		
		return repository.save(toEntity(dto));
	}

	@Override
	public Items update(Long id, ItemsDTO dto) {
		if(!id.equals(dto.getId())) {
			throw new GlobalException("Invalid Request", HttpStatus.BAD_GATEWAY, 300);			
		}
		var itemsDb = findById(dto.getId());
		
		return repository.save(toEntity(dto));
	}

	@Override
	public void delete(Long id) {
		var itemsDb = findById(id);
		repository.delete(itemsDb);
	}

	@Override
	public ItemsDTO toDto(Items entity) {
		var dto = new ItemsDTO();

		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public Items toEntity(ItemsDTO dto) {
		var entity = new Items();

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

}
