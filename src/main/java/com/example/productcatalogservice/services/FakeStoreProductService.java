package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dto.FakeStoreProductDTO;
import com.example.productcatalogservice.dtomappers.FakeStoreProductDTOMapper;
import com.example.productcatalogservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {
    private final RestClientService restClientService;

    public FakeStoreProductService(
            RestClientService restClientService
    ) {
        this.restClientService = restClientService;
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
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = this.restClientService.requestForEntity(url, HttpMethod.GET, null, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTOResponseEntity.getStatusCode().equals(HttpStatus.valueOf(200)) && fakeStoreProductDTO != null) {
            return FakeStoreProductDTOMapper.toEntity(fakeStoreProductDTO);
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
