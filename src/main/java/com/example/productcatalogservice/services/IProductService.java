package com.example.productcatalogservice.services;

import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();
    Product getProductById(Long id) throws ProductNotFoundException;
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product) throws ProductNotFoundException;
}
