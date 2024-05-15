package com.api.v1.breakfast.Breakfast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.v1.breakfast.Breakfast.models.ItemsBreakfast;
import com.api.v1.breakfast.Breakfast.models.itemsBreakfastPK;

public interface ItemBreakfastRepository extends JpaRepository<ItemsBreakfast, itemsBreakfastPK>{

}