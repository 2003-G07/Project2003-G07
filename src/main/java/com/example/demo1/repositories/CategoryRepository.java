package com.example.demo1.repositories;

import com.example.demo1.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByNamn(String namn);
}
