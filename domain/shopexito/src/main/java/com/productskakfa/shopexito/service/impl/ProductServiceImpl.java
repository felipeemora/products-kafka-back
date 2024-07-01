package com.productskakfa.shopexito.service.impl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productskakfa.shopexito.model.Product;
import com.productskakfa.shopexito.repository.ProductsRepository;
import com.productskakfa.shopexito.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductsRepository productsRepository;

    @Override
    public Flux<Product> getAll() {
        return productsRepository.findAll()
            .delayElements(Duration.ofMillis(500));
    }

    @Override
    public Flux<Product> getByCategory(String category) {
        return productsRepository
            .findByCategory(category)
            .filter(p -> p.getCategory().equals(category));
    }

    @Override
    public Mono<Product> getByCode(int code) {
        return productsRepository.findById(code);
    }

    @Override
    public Mono<Void> create(Product product) {
       return getByCode(product.getCode())
        .switchIfEmpty(Mono.just(product).flatMap(p -> productsRepository.save(p)))
        .then();
    }

    @Override
    public Mono<Product> delete(int code) {
        return getByCode(code)
            .flatMap(p -> productsRepository.deleteById(code)
                .then(Mono.just(p)
            ));
    }

    @Override
    public Mono<Product> update(int code, double price) {
        return getByCode(code)
            .flatMap(p -> {
                p.setPrice(price);
                return productsRepository.save(p);
            });
    }

}
