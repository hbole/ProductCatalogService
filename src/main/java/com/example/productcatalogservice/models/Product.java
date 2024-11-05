package com.example.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel {
    private String title;
    private String description;
    private String imageUrl;
    private Double price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    private Boolean isPrimeSpecific;
}
