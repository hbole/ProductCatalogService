package com.example.productcatalogservice.dtomappers;

import com.example.productcatalogservice.dto.CategoryDTO;
import com.example.productcatalogservice.dto.ProductDTO;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOMapper implements DTOMapper<Product, ProductDTO> {
    private CategoryDTOMapper categoryDTOMapper;

    public ProductDTOMapper(CategoryDTOMapper categoryDTOMapper) {
        this.categoryDTOMapper = categoryDTOMapper;
    }

    @Override
    public ProductDTO toDTO(Product product) {
        CategoryDTO categoryDTO = categoryDTOMapper.toDTO(product.getCategory());
        ProductDTO productDTO = new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getImageUrl(),
                product.getPrice(),
                categoryDTO
        );
        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        Category category = categoryDTOMapper.toEntity(productDTO.getCategory());

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
