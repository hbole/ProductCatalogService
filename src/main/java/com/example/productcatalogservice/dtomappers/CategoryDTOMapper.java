package com.example.productcatalogservice.dtomappers;

import com.example.productcatalogservice.dto.CategoryDTO;
import com.example.productcatalogservice.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOMapper implements DTOMapper<Category, CategoryDTO> {

    @Override
    public CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
        return categoryDTO;
    }

    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return category;
    }
}
