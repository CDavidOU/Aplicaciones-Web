package com.example.tienda.dto;

public record ProductResponse(
    long id,
    String name,
    Double price,
    int stock
) {

}
