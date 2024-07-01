package com.kafkaproducts.producttracker.service;

import com.kafkaproducts.producttracker.model.Element;

import reactor.core.publisher.Flux;

public interface ElementService {
    Flux<Element> filterByMaxPrice(double precioMax);
}
