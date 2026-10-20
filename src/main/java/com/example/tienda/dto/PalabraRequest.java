package com.example.tienda.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class PalabraRequest {
    
    @NotBlank(message = "La acción es obligatoria (add/remove)")
    private String action;
    
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private int quantity;

    public PalabraRequest(){
        
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

}
