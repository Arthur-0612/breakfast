package com.api.v1.breakfast.Breakfast.services;

import java.util.List;
import java.util.Set;

import com.api.v1.breakfast.Breakfast.models.ItemsBreakfast;

public interface ItemBreakfastService {

	List<ItemsBreakfast> save(Set<ItemsBreakfast> itemBreakfast);

}
