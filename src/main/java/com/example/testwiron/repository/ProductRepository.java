package com.example.testwiron.repository;

import com.example.testwiron.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    // for some new method like findAll() findById, save() deleteById,
}
