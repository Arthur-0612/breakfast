package com.api.v1.breakfast.Breakfast.models;

import java.util.HashSet;
import java.util.Set;

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
@Table(name = "Items")
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String name;
	
	@OneToMany(mappedBy="id.items")
    private Set<ItemsBreakfast> items = new HashSet<>();
}
