package com.example.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.model.Category;
import com.example.tienda.model.Product;
import com.example.tienda.repository.CategoryRepository;
import com.example.tienda.repository.ProductRepository;
import com.example.tienda.exception.ResourceNotFoundException;

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

    public Product save(Product product,Long categoryId){
        Category category = repoCategory.
        findById(categoryId).
        orElseThrow(
            () -> new ResourceNotFoundException("Category not found with id: "+ categoryId)
        );//Brujeria para encontrar un error, cuando no encuentra el id de la categoria, lanza una excepcion
        product.setCategory(category);
        return repo.save(product);
    }

<<<<<<< Updated upstream
    public Optional<Product> update(long id, Product product, Long categoryId){
         
        Product productBd = repo.findById(id).orElseThrow(
            ()-> new ResourceNotFoundException("CategoriId not found whit ID:" + id)
        );
        
        Category category =repoCategory.findById(categoryId).orElseThrow(
            ()-> new ResourceNotFoundException("Category not found whit id"+ categoryId)
        );

        //Product productDb = optional.get();
        productBd.setName(product.getName()+"");
        productBd.setPrice((double)product.getPrice());
        productBd.setStock(product.getStock());
        productBd.setCategory(category);
        return Optional.of(repo.save(productBd));
=======
    public Product update(long id, Product product, Long categoryId){
        Product productDb = repo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Product not found with id: " + id)
        );
        Category category = repoCategory.findById(categoryId).orElseThrow(
            // CORRECCIÓN: Imprimir categoryId en lugar de id
            () -> new ResourceNotFoundException("Category not found with id: " + categoryId) 
        );
        
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setStock(product.getStock());
        
        // CORRECCIÓN: Faltaba asignarle la categoría al producto antes de guardarlo
        productDb.setCategory(category); 

        return repo.save(productDb);
>>>>>>> Stashed changes
    }

    public Boolean delete(long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }


}