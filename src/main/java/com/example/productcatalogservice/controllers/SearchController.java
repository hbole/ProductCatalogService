package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dto.ProductDTO;
import com.example.productcatalogservice.dto.SearchProductDTO;
import com.example.productcatalogservice.dtomappers.ProductDTOMapper;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.ISearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {
    private final ISearchService searchService;

    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/products/search")
    public ResponseEntity<Page<ProductDTO>> searchProducts(@RequestBody SearchProductDTO searchProductDTO) {
        try {
            Page<Product> products = searchService.searchProducts(
                    searchProductDTO.getQuery(),
                    searchProductDTO.getPageNumber(),
                    searchProductDTO.getPageSize(),
                    searchProductDTO.getSortParams()
            );
            System.out.println(products);
            Page<ProductDTO> productDTOPage = products.map(ProductDTOMapper::toDTO);
            return new ResponseEntity<>(productDTOPage, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
