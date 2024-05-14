package com.api.v1.breakfast.Breakfast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.v1.breakfast.Breakfast.models.Breakfast;

public interface BreakfastRepository extends JpaRepository<Breakfast, Long> {

}
