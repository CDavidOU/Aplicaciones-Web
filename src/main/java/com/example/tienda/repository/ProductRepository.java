package com.example.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tienda.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}