package com.example.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel {
    private String title;
    private String description;
    private String imageUrl;
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private Boolean isPrimeSpecific;
}
