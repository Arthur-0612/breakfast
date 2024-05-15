package com.api.v1.breakfast.Breakfast.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ItemsBreakfastPK {

    @ManyToOne
    @JoinColumn(name = "breakfast_id")
    private Breakfast breakfast;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
