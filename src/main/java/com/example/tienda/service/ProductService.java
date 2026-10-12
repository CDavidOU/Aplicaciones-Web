package com.example.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.model.Product;
import com.example.tienda.repository.ProductRepository;

@Service 
public class ProductService {

    private ProductRepository repo;
    public ProductService(ProductRepository repo){
        this.repo = repo;
    }

    public List<Product> getAll(){
        return repo.findAll();
    }

    public Optional<Product> findById(Long id){
        return repo.findById(id);
    }

    public Product save(Product product){
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

        return Optional.of(productDb);
    }

    public Boolean delete(long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }


}
