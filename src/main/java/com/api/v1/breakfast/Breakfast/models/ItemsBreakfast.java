package com.api.v1.breakfast.Breakfast.models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemsBreakfast {
	
    @EmbeddedId
    private ItemsBreakfastPK id = new ItemsBreakfastPK();
    
    private Set<String> item;
    
    private boolean confirm;
    
    @JsonIgnore
    public Breakfast breakfast() {
    	return id.getBreakfast();
    }
    
    public Employee employee() {
    	return id.getEmployee();
    }
    
    public void setBreakfast(Breakfast breakfast) {
    	id.setBreakfast(breakfast);
    }
    
    public void setEmployee(Employee employee) {
    	id.setEmployee(employee);
    }
}
