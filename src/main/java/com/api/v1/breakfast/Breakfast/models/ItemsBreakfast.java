package com.api.v1.breakfast.Breakfast.models;

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
        
    private boolean confirm;
    
    @JsonIgnore
    public Breakfast breakfast() {
    	return id.getBreakfast();
    }
    
    public Employee employee() {
    	return id.getEmployee();
    }
    
    public Item item() {
    	return id.getItem();
    }
    
    public void setBreakfast(Breakfast breakfast) {
    	id.setBreakfast(breakfast);
    }
    
    public void setEmployee(Employee employee) {
    	id.setEmployee(employee);
    }
    
    public void setItem(Item item) {
    	id.setItem(item);
    } 
}
