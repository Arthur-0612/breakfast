package com.api.v1.breakfast.Breakfast.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Breakfast")
public class Breakfast {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate dateBreakfast;

	private String description;

	@OneToMany(mappedBy = "breakfast", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Employee> employees = new ArrayList<>();
}
