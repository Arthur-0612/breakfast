package com.api.v1.breakfast.Breakfast.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
    private itemsBreakfastPK id = new itemsBreakfastPK();
    
    private boolean confirm;
    
    @JsonIgnore
    public Breakfast breakfast() {
    	return id.getBreakfast();
    }
    
    public Employee employee() {
    	return id.getEmployee();
    }
    
    public Items items() {
    	return id.getItems();
    }
    
    public void setBreakfast(Breakfast breakfast) {
    	id.setBreakfast(breakfast);
    }
    
    public void setEmployee(Employee employee) {
    	id.setEmployee(employee);
    }
    
    public void setItems(Items items) {
    	id.setItems(items);
    }
    
}
