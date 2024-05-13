package com.api.v1.breakfast.Breakfast.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.v1.breakfast.Breakfast.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByCpf(String cpf);
	
	
}
