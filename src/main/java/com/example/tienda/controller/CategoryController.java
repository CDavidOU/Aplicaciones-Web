package com.example.tienda.controller;
import java.util.Optional;


import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.dto.CategoryRequest;
import com.example.tienda.dto.CategoryResponse;
import com.example.tienda.model.Category;
import com.example.tienda.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")  
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> listResponse = new ArrayList<>();
        for (Category c : service.getAll()) {
            listResponse.add(toResponse(c));
        }
        return ResponseEntity.ok(listResponse);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        Optional<Category> category = service.findById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(toResponse(category.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoryResponse>  save(@Valid @RequestBody CategoryRequest request) {
        //TODO: process POST request
        Category savedCategory = service.save(toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(savedCategory));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,@Valid @RequestBody CategoryRequest request) {
        //TODO: process PUT request
        Optional<Category> optional = service.update(id,toEntity(request));
        if (optional.isPresent()) {
            return ResponseEntity.ok(toResponse(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    private Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        return category;
    }
    

}
