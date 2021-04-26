package com.example.demo1.repositories;

import com.example.demo1.models.ProductCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
    Optional<ProductCategory> findById(Long id);
}
