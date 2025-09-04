package com.github.leopc17.inventorymanager.domain.repository;

import com.github.leopc17.inventorymanager.domain.entity.Product;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface ProductRepository extends Repository<Product, Integer> {

    Optional<Product> findById(Integer id);
    Optional<Product> findByName(String name);

}

