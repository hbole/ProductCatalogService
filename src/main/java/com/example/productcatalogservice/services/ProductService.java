package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dto.FakeStoreProductDTO;
import com.example.productcatalogservice.dtomappers.FakeStoreProductDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.productcatalogservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreProductDTOMapper fakeStoreProductDTOMapper;

    public ProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreProductDTOMapper fakeStoreProductDTOMapper) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreProductDTOMapper = fakeStoreProductDTOMapper;
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) {
        String url = "https://fakestoreapi.com/products/{productId}";
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.getForEntity(url, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTOResponseEntity.getStatusCode().equals(HttpStatus.valueOf(200)) && fakeStoreProductDTO !=null) {
            this.fakeStoreProductDTOMapper.toEntity(fakeStoreProductDTO);
        }
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return product;
    }
}
