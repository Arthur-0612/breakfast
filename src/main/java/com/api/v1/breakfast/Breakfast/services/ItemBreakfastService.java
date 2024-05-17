package com.api.v1.breakfast.Breakfast.services;

import java.util.List;

import com.api.v1.breakfast.Breakfast.models.ItemsBreakfast;

public interface ItemBreakfastService {
	
	List<ItemsBreakfast> saveAll(List<ItemsBreakfast> itemBreakfast);

}
