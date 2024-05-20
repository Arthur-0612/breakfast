package com.api.v1.breakfast.Breakfast.services.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.v1.breakfast.Breakfast.Exceptions.GlobalException;
import com.api.v1.breakfast.Breakfast.dto.BreakfastDTO;
import com.api.v1.breakfast.Breakfast.dto.EmployeeDTO;
import com.api.v1.breakfast.Breakfast.dto.ItemDTO;
import com.api.v1.breakfast.Breakfast.models.Breakfast;
import com.api.v1.breakfast.Breakfast.models.Employee;
import com.api.v1.breakfast.Breakfast.models.Item;
import com.api.v1.breakfast.Breakfast.repositories.BreakfastRepository;
import com.api.v1.breakfast.Breakfast.services.BreakfastService;
import com.api.v1.breakfast.Breakfast.services.EmployeeService;
import com.api.v1.breakfast.Breakfast.services.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreakfastServiceImpl implements BreakfastService {
	
	@Autowired
	private BreakfastRepository breakfastRepository;
	@Autowired
	private ItemService itemService;
	@Autowired
	private EmployeeService employeeService;

	@Override
	public Breakfast findById(Long id) {
		return breakfastRepository.findById(id)
				.orElseThrow(() -> new GlobalException("Breakfast not found", HttpStatus.BAD_GATEWAY, 100));
	}

	@Override
	public List<BreakfastDTO> findAll() {
	    List<Breakfast> breakfastList = breakfastRepository.findAll();
	    return breakfastList.stream()
	            .map(this::toDto) // Mapeie os resultados para DTOs
	            .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Breakfast save(BreakfastDTO dto) {
	    Breakfast breakfast = new Breakfast();
	    breakfast.setDateBreakfast(dto.getDateBreakfast());
	    breakfast.setDescription(dto.getDescription());

	    if (dto.getDateBreakfast().isBefore(LocalDate.now())) {
	        throw new GlobalException("The breakfast date cannot be less than or equal to the current date",
	                HttpStatus.BAD_REQUEST, 200);
	    }

	    if (dto.getEmployees() != null) {
	        List<Employee> employees = dto.getEmployees().stream().map(e -> {
	            Employee employee = employeeService.findById(e.getId());
	            if (e.getItems() != null) {
	                Set<Item> items = e.getItems().stream()
	                    .map(itemDTO -> itemService.findById(itemDTO.getId()))
	                    .collect(Collectors.toSet());
	                employee.setItems(items);
	            }
	            employee.setBreakfast(breakfast);
	            return employee;
	        }).collect(Collectors.toList());
	        breakfast.setEmployees(employees);
	    }

	    return breakfastRepository.save(breakfast);
	}


	@Override
	@Transactional
	public Breakfast update(Long id, BreakfastDTO dto) {
		Breakfast existingBreakfast = findById(id);
		existingBreakfast.setDateBreakfast(dto.getDateBreakfast());
		existingBreakfast.setDescription(dto.getDescription());
		return breakfastRepository.save(existingBreakfast);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Breakfast breakfast = findById(id);
		breakfastRepository.delete(breakfast);
	}

	@Override
	public BreakfastDTO toDto(Breakfast entity) {
	    BreakfastDTO dto = new BreakfastDTO();
	    dto.setId(entity.getId());
	    dto.setDateBreakfast(entity.getDateBreakfast());
	    dto.setDescription(entity.getDescription());

	    List<EmployeeDTO> employeeDTOs = entity.getEmployees().stream()
	        .map(employee -> {
	            EmployeeDTO employeeDTO = new EmployeeDTO();
	            employeeDTO.setId(employee.getId());
	            employeeDTO.setName(employee.getName());
	            employeeDTO.setCpf(employee.getCpf());
	            employeeDTO.setStatus(employee.getStatus());

	            Set<ItemDTO> itemDTOs = employee.getItems().stream()
	                .map(item -> {
	                    ItemDTO itemDTO = new ItemDTO();
	                    itemDTO.setId(item.getId());
	                    itemDTO.setDescription(item.getDescription());
	                    return itemDTO;
	                })
	                .collect(Collectors.toSet());

	            employeeDTO.setItems(itemDTOs);
	            return employeeDTO;
	        })
	        .collect(Collectors.toList());
	    dto.setEmployees(employeeDTOs);

	    return dto;
	}



	@Override
	public Breakfast toEntity(BreakfastDTO dto) {
	    Breakfast breakfast = new Breakfast();
	    breakfast.setId(dto.getId());
	    breakfast.setDateBreakfast(dto.getDateBreakfast());
	    breakfast.setDescription(dto.getDescription());

	    if (dto.getEmployees() != null) {
	        List<Employee> employees = dto.getEmployees().stream().map(e -> {
	            Employee employee = employeeService.findById(e.getId());
	            employee.setBreakfast(breakfast);
	            if (e.getItems() != null) {
	                Set<Item> items = e.getItems().stream()
	                    .map(itemDTO -> {
	                        Item item = itemService.findById(itemDTO.getId());
	                        return item;
	                    })
	                    .collect(Collectors.toSet());
	                employee.setItems(items);
	            }
	            return employee;
	        }).collect(Collectors.toList());
	        breakfast.setEmployees(employees);
	    }

	    return breakfast;
	}



}
