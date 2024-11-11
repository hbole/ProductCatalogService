package com.example.productcatalogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "categories")
public class Category extends BaseModel implements Serializable {
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
