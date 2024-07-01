package com.kafkaproducts.producttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kafkaproducts.producttracker.model.Element;
import com.kafkaproducts.producttracker.service.ElementService;

import reactor.core.publisher.Flux;

@CrossOrigin("*")
@RestController
public class ProductTrackerController {
	@Autowired
	ElementService elementService;

	@GetMapping(value="elements/{price}")
	public ResponseEntity<Flux<Element>> elementsByMaxPrice(@PathVariable("price") double maxPrice) {
		return new ResponseEntity<>(elementService.filterByMaxPrice(maxPrice),HttpStatus.OK);
	}
}
