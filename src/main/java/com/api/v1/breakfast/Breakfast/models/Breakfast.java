package com.api.v1.breakfast.Breakfast.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
	Long id;
	
	LocalDate dateBreakfast;

	String descricao;

	@OneToMany(mappedBy="id.breakfast")
    private Set<ItemsBreakfast> breakfast = new HashSet<>();
	
}
