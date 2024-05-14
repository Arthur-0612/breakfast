package com.api.v1.breakfast.Breakfast.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	Long id;

	LocalDate dateBreakfast;

	String descricao;

}
