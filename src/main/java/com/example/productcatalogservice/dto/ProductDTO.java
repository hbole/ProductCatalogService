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
}
