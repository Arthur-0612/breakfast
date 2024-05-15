package com.api.v1.breakfast.Breakfast.services.Impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.v1.breakfast.Breakfast.Exceptions.GlobalException;
import com.api.v1.breakfast.Breakfast.dto.BreakfastDTO;
import com.api.v1.breakfast.Breakfast.dto.EmployeeDTO;
import com.api.v1.breakfast.Breakfast.dto.ItemsDTO;
import com.api.v1.breakfast.Breakfast.models.Breakfast;
import com.api.v1.breakfast.Breakfast.models.Employee;
import com.api.v1.breakfast.Breakfast.models.Items;
import com.api.v1.breakfast.Breakfast.models.ItemsBreakfast;
import com.api.v1.breakfast.Breakfast.repositories.BreakfastRepository;
import com.api.v1.breakfast.Breakfast.services.BreakfastService;
import com.api.v1.breakfast.Breakfast.services.EmployeeService;
import com.api.v1.breakfast.Breakfast.services.ItemBreakfastService;
import com.api.v1.breakfast.Breakfast.services.ItemsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreakfastServiceImpl implements BreakfastService {

	private final BreakfastRepository repository;
	private final EmployeeService service;
	private final ItemsService itemsService;
	private final ItemBreakfastService itemsBreakfastService;

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
	public Breakfast save(BreakfastDTO dto) {
		var breakfast = toEntity(dto);

		if (breakfast.getDateBreakfast().isBefore(LocalDate.now())) {
			throw new GlobalException("The breakfast date cannot be less than or equal to the current date",
					HttpStatus.BAD_REQUEST, 200);
		}

		var breakfastDb = repository.save(breakfast);

		breakfastDb.getBreakfast().forEach(elem -> {
			elem.setBreakfast(breakfastDb);
		});

		itemsBreakfastService.save(breakfastDb.getBreakfast());

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
		dto.setDescricao(entity.getDescricao());
		return dto;
	}

	@Override
	public Breakfast toEntity(BreakfastDTO dto) {
		var entity = new Breakfast();

		entity.setId(dto.getId());
		entity.setDateBreakfast(dto.getDateBreakfast());
		entity.setDescricao(dto.getDescricao());
		
		Set<ItemsBreakfast> itemsBreakfastSet = new HashSet<>();

		dto.getEmployee().forEach(elem -> {
			var itemsBreakfast = new ItemsBreakfast();
			itemsBreakfast.setEmployee(service.findById(elem.getId()));
			itemsBreakfastSet.add(itemsBreakfast);

		});
		
		dto.getItems().forEach(elem -> {
			var itemsBreakfast = new ItemsBreakfast();
			itemsBreakfast.setItems(itemsService.findById(elem.getId()));
			itemsBreakfastSet.add(itemsBreakfast);
		});
		
		entity.setBreakfast(itemsBreakfastSet);
		return entity;
	}
}
