package com.example.productcatalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private CategoryDTO category;

//    public ProductDTO(Long id, String title, String description, String imageUrl, Double price) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.imageUrl = imageUrl;
//        this.price = price;
//    }
}
