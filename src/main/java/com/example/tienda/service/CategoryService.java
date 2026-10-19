package com.example.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.exception.ResourceNotFoundException;
import com.example.tienda.model.Category;
import com.example.tienda.repository.CategoryRepository;

@Service 
public class CategoryService {
    
    private final CategoryRepository repoCategory;

   public CategoryService(CategoryRepository repo) {
        this.repoCategory = repo;
    }

    public List<Category> getAll() {
        return repoCategory.findAll();
    }

    public Optional<Category> findById(Long id) {
        return repoCategory.findById(id);
    }

    public Category save(Category category) {
        return repoCategory.save(category);
    }

    public Optional<Category> update(long id,Category category){
        Category categoryBd = repoCategory.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Category not found whit id: " + id)
        );

    categoryBd.setName(category.getName());
    return Optional.of(repoCategory.save(categoryBd));
    }

    public Boolean delete(Long id) {
        if (!repoCategory.existsById(id)) {
            return false;
        }
        repoCategory.deleteById(id);
        return true;
    }
    
}
