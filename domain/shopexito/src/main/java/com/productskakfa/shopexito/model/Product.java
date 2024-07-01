package com.productskakfa.shopexito.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private int code;
    private String name;
    private String category;
    private double price;
    private int stock;
}
