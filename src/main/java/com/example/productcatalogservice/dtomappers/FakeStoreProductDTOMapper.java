package com.example.productcatalogservice.dtomappers;

import com.example.productcatalogservice.dto.FakeStoreProductDTO;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.stereotype.Component;

@Component
public class FakeStoreProductDTOMapper implements DTOMapper<Product, FakeStoreProductDTO> {
    @Override
    public FakeStoreProductDTO toDTO(Product product) {
        return null;
    }

    @Override
    public Product toEntity(FakeStoreProductDTO fakeStoreProductDTO) {
        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());

        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageUrl(fakeStoreProductDTO.getImage());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setCategory(category);
        product.setIsPrimeSpecific(true);

        return product;
    }
}
