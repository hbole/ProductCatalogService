package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dto.SortParamDTO;
import com.example.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(String query, int pageNumber, int pageSize, List<SortParamDTO> sortParams);
}
