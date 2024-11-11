package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dto.FakeStoreProductDTO;
import com.example.productcatalogservice.dtomappers.FakeStoreProductDTOMapper;
import com.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements IProductService {
    private final RestClientService restClientService;
    private final RedisTemplate<String, Object> productRedisTemplate;
    private final RedisTemplate redisTemplate;

    public FakeStoreProductService(
            RestClientService restClientService,
            RedisTemplate<String, Object> productRedisTemplate,
            @Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        this.restClientService = restClientService;
        this.productRedisTemplate = productRedisTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String url = "https://fakestoreapi.com/products";
        FakeStoreProductDTO[] fakeStoreProductDTOs = this.restClientService.requestForEntity(url, HttpMethod.GET, null, FakeStoreProductDTO[].class).getBody();

        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOs) {
            products.add(FakeStoreProductDTOMapper.toEntity(fakeStoreProductDTO));
        }

        return products;
    }

    @Override
    public Product getProductById(Long id) {
        String url = "https://fakestoreapi.com/products/{productId}";
        Product product = null;

        product = (Product) redisTemplate.opsForHash().get("__PRODUCTS__", id);
        if(product != null) {
            return product;
        }

        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = this.restClientService.requestForEntity(url, HttpMethod.GET, null, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTOResponseEntity.getStatusCode().equals(HttpStatus.valueOf(200)) && fakeStoreProductDTO != null) {
            product = FakeStoreProductDTOMapper.toEntity(fakeStoreProductDTO);
            redisTemplate.opsForHash().put("__PRODUCTS__", id, product);
            return product;
        }
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        String url = "https://fakestoreapi.com/products";

        FakeStoreProductDTO fakeStoreProductDTO = FakeStoreProductDTOMapper.toDTO(product);
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = this.restClientService.requestForEntity(url, HttpMethod.POST, fakeStoreProductDTO, FakeStoreProductDTO.class);
        return FakeStoreProductDTOMapper.toEntity(fakeStoreProductDTOResponseEntity.getBody());
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        String url = "https://fakestoreapi.com/products/{productId}";

        FakeStoreProductDTO fakeStoreProductDTO = FakeStoreProductDTOMapper.toDTO(product);
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = this.restClientService.requestForEntity(url, HttpMethod.PUT, fakeStoreProductDTO, FakeStoreProductDTO.class, id);
        return FakeStoreProductDTOMapper.toEntity(fakeStoreProductDTOResponseEntity.getBody());
    }
}
