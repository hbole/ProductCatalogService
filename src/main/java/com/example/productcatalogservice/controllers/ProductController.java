package com.example.productcatalogservice.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.dto.ProductDTO;
import com.example.productcatalogservice.dtomappers.DTOMapper;
import com.example.productcatalogservice.services.IProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final IProductService productService;
    private final DTOMapper<Product, ProductDTO> productDTOMapper;

    public ProductController(
            IProductService productService,
            DTOMapper<Product, ProductDTO> productDTOMapper
    ) {
        this.productService = productService;
        this.productDTOMapper = productDTOMapper;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<Product> products = productService.getProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();

        if(products != null && !products.isEmpty()) {
            for (Product product : products) {
                productDTOs.add(productDTOMapper.toDTO(product));
            }
        }

        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long productId) throws ProductNotFoundException {
        try {
            if(productId < 1 || productId > 20) {
                throw new ProductNotFoundException("Invalid product id");
            }

            Product product = productService.getProductById(productId);
            if(product == null) {
//                return null;
                throw new ProductNotFoundException("Product not found");
            }
            ProductDTO productDTO = productDTOMapper.toDTO(product);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (ProductNotFoundException exception) {
            //return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            throw exception;
        }
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            Product newProduct = productDTOMapper.toEntity(productDTO);
            newProduct = productService.addProduct(newProduct);
            return new ResponseEntity<>(productDTOMapper.toDTO(newProduct), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDTO productDTO) {
        try {
            Product product = productDTOMapper.toEntity(productDTO);
            product = productService.updateProduct(productId, product);

            return new ResponseEntity<>(productDTOMapper.toDTO(product), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
