package com.example.productcatalogservice.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.dto.CategoryDTO;
import com.example.productcatalogservice.dto.ProductDTO;
import com.example.productcatalogservice.dtomappers.DTOMapper;
import com.example.productcatalogservice.services.IProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
//    @Autowired
    private IProductService productService;
//    @Autowired
    private DTOMapper<Product, ProductDTO> productDTOMapper;
//    @Autowired
    private DTOMapper<Category, CategoryDTO> categoryDTOMapper;

    public ProductController(
            IProductService productService,
            DTOMapper<Product, ProductDTO> productDTOMapper,
            DTOMapper<Category, CategoryDTO> categoryDTOMapper
    ) {
        this.productService = productService;
        this.productDTOMapper = productDTOMapper;
        this.categoryDTOMapper = categoryDTOMapper;
    }

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {
        List<Product> products = productService.getProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(productDTOMapper.toDTO(product));
        }
        return productDTOs;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long productId) {
        try {
            if(productId < 1 || productId > 20) {
                throw new ProductNotFoundException("Product not found");
            }

            Product product = productService.getProductById(productId);
            if(product == null) {
                return null;
            }
            ProductDTO productDTO = productDTOMapper.toDTO(product);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO product) {
        Product newProduct = productDTOMapper.toEntity(product);
        Product savedProduct = productService.addProduct(newProduct);
        return productDTOMapper.toDTO(savedProduct);
    }
}
