package com.productskakfa.shopexito.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(value = "products")
public class Product implements Persistable<Integer> {
    @Id
    private int code;
    private String name;
    private String category;
    private double price;
    private int stock;

    @Transient
    private boolean isNew;

    @Override
    public Integer getId() {
        return code;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
