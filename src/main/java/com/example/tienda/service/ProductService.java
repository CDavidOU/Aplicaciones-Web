package com.example.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public Product save(Product product,Long categoryId){
        Category category = repoCategory.findById(categoryId).orElseThrow();//Brujeria para encontrar un error, cuando no encuentra el id de la categoria, lanza una excepcion
        product.setCategory(category);
        return repo.save(product);
    }

    public Optional<Product> update(long id, Product product){
        Optional<Product> optional = repo.findById(id);
        if(optional.isEmpty()){
            return Optional.empty();
        }
        Product productDb = optional.get();
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setStock(product.getStock());
        return Optional.of(repo.save(productDb));
    }

    public Boolean delete(long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }


}