package com.api.v1.breakfast.Breakfast.services.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.v1.breakfast.Breakfast.Exceptions.GlobalException;
import com.api.v1.breakfast.Breakfast.dto.BreakfastDTO;
import com.api.v1.breakfast.Breakfast.models.Breakfast;
import com.api.v1.breakfast.Breakfast.models.ItemsBreakfast;
import com.api.v1.breakfast.Breakfast.repositories.BreakfastRepository;
import com.api.v1.breakfast.Breakfast.services.BreakfastService;
import com.api.v1.breakfast.Breakfast.services.EmployeeService;
import com.api.v1.breakfast.Breakfast.services.ItemBreakfastService;
import com.api.v1.breakfast.Breakfast.services.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreakfastServiceImpl implements BreakfastService {

	private final BreakfastRepository repository;
	private final EmployeeService service;
	private final ItemBreakfastService itemsBreakfastService;
	private final ItemService itemService;

	@Override
	public Breakfast findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new GlobalException("Breakfast not found", HttpStatus.BAD_GATEWAY, 100));
	}

	@Override
	public List<Breakfast> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public Breakfast save(BreakfastDTO dto) {
	    var breakfast = toEntity(dto);

	    if (breakfast.getDateBreakfast().isBefore(LocalDate.now())) {
	        throw new GlobalException("The breakfast date cannot be less than or equal to the current date",
	                HttpStatus.BAD_REQUEST, 200);
	    }

	    var breakfastDb = repository.save(breakfast);

	    List<ItemsBreakfast> itemsBreakfastList = new ArrayList<>();
	    Set<String> addedItems = new HashSet<>();

	    dto.getEmployee().forEach(elem -> {
	        var employee = service.findById(elem.getId());
	        elem.getItems().forEach(item -> {
	            String itemKey = employee.getId() + "-" + item.getId();
	            if (addedItems.contains(itemKey)) {
	                throw new GlobalException("Item with ID " + item.getId() + " already added for employee " + employee.getId(),
	                        HttpStatus.BAD_REQUEST, 200);
	            }
	            addedItems.add(itemKey);

	            var itemsBreakfast = new ItemsBreakfast();
	            itemsBreakfast.setBreakfast(breakfastDb);
	            itemsBreakfast.setEmployee(employee);
	            itemsBreakfast.setItem(itemService.findById(item.getId()));

	            itemsBreakfastList.add(itemsBreakfast);
	        });
	    });

	    List<ItemsBreakfast> itemsBreakfast = itemsBreakfastService.saveAll(itemsBreakfastList);

	    breakfastDb.setBreakfast(itemsBreakfast);

	    return breakfastDb;
	}


	@Override
	public Breakfast update(Long id, BreakfastDTO dto) {
		if (!id.equals(dto.getId())) {
			throw new GlobalException("Invalid Request", HttpStatus.BAD_REQUEST, 300);
		}

		var breakfastDb = findById(id);

		return repository.save(breakfastDb);
	}

	@Override
	public void delete(Long id) {
		var breakfastDb = findById(id);

		repository.delete(breakfastDb);
	}

	@Override
	public BreakfastDTO toDto(Breakfast entity) {
		var dto = new BreakfastDTO();
		dto.setId(entity.getId());
		dto.setDateBreakfast(entity.getDateBreakfast());
		dto.setDescription(entity.getDescription());
		return dto;
	}

	@Override
	public Breakfast toEntity(BreakfastDTO dto) {
		var entity = new Breakfast();
		entity.setDateBreakfast(dto.getDateBreakfast());
		entity.setDescription(dto.getDescription());

		return entity;
	}
}
