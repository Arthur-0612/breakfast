package com.api.v1.breakfast.Breakfast.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.v1.breakfast.Breakfast.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	Optional<Item> findByDescription(String description);
}
