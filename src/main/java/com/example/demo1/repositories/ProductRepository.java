package com.example.demo1.repositories;

import com.example.demo1.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);

    Product getProductById(long id);

    List<Product> findByImage(String image);

    Optional<Product> findById(Long id);

}
