package com.api.v1.breakfast.Breakfast.services.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;


import com.api.v1.breakfast.Breakfast.models.ItemsBreakfast;
import com.api.v1.breakfast.Breakfast.repositories.ItemBreakfastRepository;
import com.api.v1.breakfast.Breakfast.services.ItemBreakfastService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemBreakfastServiceImpl implements ItemBreakfastService{

	private final ItemBreakfastRepository repository;

	@Override
	public List<ItemsBreakfast> save(Set<ItemsBreakfast> itemBreakfast) {
		
		return repository.saveAll(itemBreakfast);
	}
	
	
}
