package com.example.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.dto.PalabraRequest;
import com.example.tienda.exception.ResourceNotFoundException;
import com.example.tienda.model.Category;
import com.example.tienda.model.Product;
import com.example.tienda.repository.CategoryRepository;
import com.example.tienda.repository.ProductRepository;

@Service 
public class ProductService {

    private ProductRepository repo;
    private CategoryRepository repoCategory;    

    public ProductService(ProductRepository repo, CategoryRepository repoCategory){
        this.repoCategory = repoCategory;
        this.repo = repo;
    }

    public List<Product> getAll(){
        return repo.findAll();
    }

    public Optional<Product> findById(Long id){
        return repo.findById(id);
    }

    public Product save(Product product, Long categoryId){
        Category category = repoCategory.findById(categoryId).orElseThrow(
            () -> new ResourceNotFoundException("Category not found with id: "+ categoryId)
        );
        product.setCategory(category);
        return repo.save(product);
    }

    public Optional<Product> update(long id, Product product, Long categoryId){
        Product productDb = repo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Product not found with id: " + id)
        );
        Category category = repoCategory.findById(categoryId).orElseThrow(
            () -> new ResourceNotFoundException("Category not found with id: " + categoryId) 
        );
        
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setStock(product.getStock());
        productDb.setCategory(category); 

        return Optional.of(repo.save(productDb));
    }

    public Boolean delete(long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    public Product modify(long id, PalabraRequest palabra) {
        Product productDb = repo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Product not found with id: " + id)
        ); 
        
        if ("add".equalsIgnoreCase(palabra.getAction())) {
            productDb.setStock(productDb.getStock() + palabra.getQuantity());
        } else if ("remove".equalsIgnoreCase(palabra.getAction())) {
            if (palabra.getQuantity() > productDb.getStock()) {
                throw new ResourceNotFoundException("No hay suficiente stock. Stock actual: " + productDb.getStock());
            }
            productDb.setStock(productDb.getStock() - palabra.getQuantity());
        } else {
            throw new ResourceNotFoundException("Acción no válida: '" + palabra.getAction() + "'. Debe ser 'add' o 'remove'.");
        }
        
        return repo.save(productDb);
    }
}