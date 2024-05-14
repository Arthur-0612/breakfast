package com.api.v1.breakfast.Breakfast.services.Impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.v1.breakfast.Breakfast.Exceptions.GlobalException;
import com.api.v1.breakfast.Breakfast.dto.EmployeeDTO;
import com.api.v1.breakfast.Breakfast.models.Employee;
import com.api.v1.breakfast.Breakfast.repositories.EmployeeRepository;
import com.api.v1.breakfast.Breakfast.services.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository repository;

	@Override
	public Employee findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new GlobalException("Employee not found", HttpStatus.BAD_REQUEST, 100));
	}
	
	@Override
	public List<Employee> findAll() {
        return repository.findAll();
    }

	@Override
	public Employee save(EmployeeDTO dto) {
		validateCpf(dto.getCpf());
		var employeeDb = toEntity(dto);
		return repository.save(employeeDb);
	}

	@Override
	public Employee update(Long id, EmployeeDTO dto) {
		if (!id.equals(dto.getId())) {
			throw new GlobalException("Invalid Request", HttpStatus.BAD_GATEWAY, 200);
		}
		var employeeDb = findById(dto.getId());

		if (!employeeDb.getCpf().equals(dto.getCpf())) {
			validateCpf(dto.getCpf());
		}
		return repository.save(toEntity(dto));
	}
	
	@Override
	public void delete(Long id) {
		var employeeDb = findById(id);
		repository.delete(employeeDb);
	}

	private void validateCpf(String cpf) {
		var employee = repository.findByCpf(cpf);

		if (employee.isPresent()) {
			throw new GlobalException("This CPF already exist", HttpStatus.BAD_REQUEST, 300);
		}
	}

	@Override
	public EmployeeDTO toDto(Employee entity) {
		var dto = new EmployeeDTO();

		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCpf(entity.getCpf());
		return dto;
	}

	@Override
	public Employee toEntity(EmployeeDTO dto) {
		var entity = new Employee();

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());

		return entity;
	}
}
