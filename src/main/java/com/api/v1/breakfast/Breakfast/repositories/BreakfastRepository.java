package com.api.v1.breakfast.Breakfast.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.v1.breakfast.Breakfast.models.Breakfast;

public interface BreakfastRepository extends JpaRepository<Breakfast, Long> {

	
//	@Query("SELECT DISTINCT bf " +
//		       "FROM Breakfast bf " +
//		       "JOIN FETCH bf.breakfast ib " +
//		       "JOIN FETCH ib.employee emp " +
//		       "JOIN FETCH ib.item itm")
//		List<Breakfast> findByBreakfast();

}
