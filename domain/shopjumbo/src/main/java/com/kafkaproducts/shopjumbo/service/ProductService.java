package com.kafkaproducts.shopjumbo.service;

import com.kafkaproducts.shopjumbo.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<Product> getAll();
    Flux<Product> getByCategory(String category);
    Mono<Product> getByCode(int code);
    Mono<Void> create(Product product);
    Mono<Product> delete(int code);
    Mono<Product> update(int code, double price);
}
