package com.example.tienda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest( 
    @NotBlank //este es para que no sea nulo ni vacio
    String name,
    @NotNull
    @Positive
    double price,
    @NotNull //este es para que no sea nulo, pero puede ser 0
    @PositiveOrZero
    Integer stock
) {
    
}