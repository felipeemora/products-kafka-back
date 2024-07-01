package com.productskakfa.shopexito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productskakfa.shopexito.model.Product;
import com.productskakfa.shopexito.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
public class ProductsController {

    @Autowired
    ProductService productService;

    @GetMapping("products")
    public ResponseEntity<Flux<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("products/{category}")
    public ResponseEntity<Flux<Product>> byCategory(@PathVariable("category") String category) {
        return new ResponseEntity<>(productService.getByCategory(category), HttpStatus.OK);
    }
    
    @GetMapping("product")
    public ResponseEntity<Mono<Product>> byCode(@RequestParam("code") int code) {
        return new ResponseEntity<>(productService.getByCode(code), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Mono<Void>> create(@RequestBody Product product) {
        product.setNew(true);
        return new ResponseEntity<>(productService.create(product), HttpStatus.OK);
    }
    
    @PutMapping("update/{code}")
    public Mono<ResponseEntity<Product>> update(@PathVariable("code") int code, @RequestParam("price") double price) { 
        return productService.update(code, price)
            .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
            .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("delete")
    public Mono<ResponseEntity<Product>> delete(@RequestParam("code") int code) {
        return productService.delete(code)
            .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
            .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));       
    }
    
}
