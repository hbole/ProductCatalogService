package com.example.productcatalogservice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.productcatalogservice.models.Product;



@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        return null;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setTitle("iPhone16");
        product.setDescription("Yet another iPhone :(");
        product.setPrice(130000D);
        return product;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return null;
    }
}
