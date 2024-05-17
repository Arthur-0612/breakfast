package com.api.v1.breakfast.Breakfast.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	
	private String status;
	
	@OneToMany(mappedBy="id.employee", fetch = FetchType.EAGER )
    private Set<ItemsBreakfast> breakfast = new HashSet<>();
	
}
