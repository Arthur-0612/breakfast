package com.api.v1.breakfast.Breakfast.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.v1.breakfast.Breakfast.models.Items;

public interface ItemsRepository extends JpaRepository<Items, Long> {

	Optional<Items> findByName(String name);
	
}
