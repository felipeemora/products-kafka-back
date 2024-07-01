package com.productskakfa.shopexito.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.productskakfa.shopexito.model.Product;

import reactor.core.publisher.Flux;

public interface ProductsRepository extends R2dbcRepository<Product, Integer> {
    Flux<Product> findByCategory(String category);
}
