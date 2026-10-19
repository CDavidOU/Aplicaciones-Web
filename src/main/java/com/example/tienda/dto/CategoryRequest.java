package com.example.tienda.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest (
    @NotBlank(message = "La categoria no puede ir vacia")
    String name
){

}
