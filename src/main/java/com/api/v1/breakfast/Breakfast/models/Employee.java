package com.api.v1.breakfast.Breakfast.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 210)
	private String name;
	
	@Column(nullable = false, length = 14)
	private String cpf;
	
	@OneToMany(mappedBy="id.employee")
    private Set<ItemsBreakfast> employee = new HashSet<>();
}
