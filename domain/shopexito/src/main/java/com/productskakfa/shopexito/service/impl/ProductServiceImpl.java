package com.productskakfa.shopexito.service.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.productskakfa.shopexito.model.Product;
import com.productskakfa.shopexito.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private static final List<Product> products = new ArrayList<>(List.of(
        new Product(100,"Azucar","Alimentación",1.10,20),
        new Product(111,"Pan","Alimentación",1.3,10),
        new Product(112,"Esponja","Limpieza",2,20),
        new Product(113,"Sofá","Hogar",80,4),
        new Product(114,"Jarrón","Hogar",40,10),
        new Product(115,"Arina","Alimentación",3,30),
        new Product(116,"Fregona","Limpieza",3.40,6),
        new Product(117,"Cubo","Limpieza",2.5,12))
    );

    @Override
    public Flux<Product> getAll() {
        return Flux.fromIterable(products)
            .delayElements(Duration.ofMillis(500));
    }

    @Override
    public Flux<Product> getByCategory(String category) {
        return getAll()
            .filter(p -> p.getCategory().equals(category));
    }

    @Override
    public Mono<Product> getByCode(int code) {
        return getAll()
            .filter(p -> p.getCode() == code)
            .next();
    }

    @Override
    public Mono<Void> create(Product product) {
       return getByCode(product.getCode())
        .switchIfEmpty(Mono.just(product).map(p -> {
            products.add(product);
            return product;
        }))
        .then();
    }

    @Override
    public Mono<Product> delete(int code) {
        return getByCode(code)
            .map(p -> {
                products.removeIf(r-> r.getCode() == code);
                return p;
            });
    }

    @Override
    public Mono<Product> update(int code, double price) {
        return getByCode(code)
            .map(p -> {
                p.setPrice(price);
                return p;
            });
    }

}
