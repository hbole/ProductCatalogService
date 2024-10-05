package com.example.productcatalogservice.dtomappers;

import com.example.productcatalogservice.dto.CategoryDTO;
import com.example.productcatalogservice.dto.ProductDTO;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOMapper {
    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImageUrl());

        if(product.getCategory() != null) {
            CategoryDTO categoryDTO = CategoryDTOMapper.toDTO(product.getCategory());
            productDTO.setCategory(categoryDTO);
        }

        return productDTO;
    }

    public static Product toEntity(ProductDTO productDTO) {
        Category category = CategoryDTOMapper.toEntity(productDTO.getCategory());

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        product.setIsPrimeSpecific(true);

        return product;
    }
}
