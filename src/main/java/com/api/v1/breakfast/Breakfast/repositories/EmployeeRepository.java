package com.api.v1.breakfast.Breakfast.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.v1.breakfast.Breakfast.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByCpf(String cpf);
	
	@Query("SELECT obj FROM Employee obj WHERE obj.status = :status")
    List<Employee> findByStatus(String status);
}
