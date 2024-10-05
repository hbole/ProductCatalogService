package com.example.productcatalogservice.dtomappers;

import com.example.productcatalogservice.dto.CategoryDTO;
import com.example.productcatalogservice.models.Category;

public class CategoryDTOMapper {
    public static CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
        return categoryDTO;
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return category;
    }
}
