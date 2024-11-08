package com.example.productcatalogservice.services;

import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.repositories.CategoryRepository;
import com.example.productcatalogservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.productcatalogservice.models.Product;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> optionalCategory = this.categoryRepository.findByName(product.getCategory().getName());
        if(optionalCategory.isPresent()) {
            product.setCategory(optionalCategory.get());
        }
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if(optionalProduct.isPresent()) {
            return this.productRepository.save(product);
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }
}
