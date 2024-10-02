package com.example.productcatalogservice.dtomappers;

import com.example.productcatalogservice.dto.CategoryDTO;
import com.example.productcatalogservice.dto.ProductDTO;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOMapper implements DTOMapper<Product, ProductDTO> {
    private final CategoryDTOMapper categoryDTOMapper;

    public ProductDTOMapper(CategoryDTOMapper categoryDTOMapper) {
        this.categoryDTOMapper = categoryDTOMapper;
    }

    @Override
    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getImageUrl(),
                product.getPrice()
        );

        if(product.getCategory() != null) {
            CategoryDTO categoryDTO = categoryDTOMapper.toDTO(product.getCategory());
            productDTO.setCategory(categoryDTO);
        }

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
