package com.example.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private Double price;
}
