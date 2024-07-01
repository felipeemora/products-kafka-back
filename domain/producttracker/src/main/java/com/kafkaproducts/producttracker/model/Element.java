package com.kafkaproducts.producttracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Element {
    private String name;
    private String category;
    private double price;
    private String shop;
}
