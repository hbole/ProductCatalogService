package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dto.ProductDTO;
import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private IProductService productService;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    public void Test_GetProductById_WithValidId_RunSuccessfully() throws ProductNotFoundException {
        //Arrange
        Long productId = 15L;
        Product product = new Product();
        product.setId(productId);
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.00);


        when(productService.getProductById(productId)).thenReturn(
                product
        );

        //Act
        ResponseEntity<ProductDTO> response = productController.getProductById(productId);

        //Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 15L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void Test_GetProductById_WithInvalidId_ThrowsException() {
        //Act and Assert
        ProductNotFoundException exception = assertThrows(
                ProductNotFoundException.class,
                () -> productController.getProductById(-1L)
        );

        assertEquals(exception.getMessage(), "Invalid product id");
    }

    @Test
    public void Test_GetProductById_WithValidId_ThrowsException() throws ProductNotFoundException {
        Long productId = 1L;

        //Arrange
        when(productService.getProductById(productId)).thenReturn(null);

        //Act And Assert
        assertThrows(
                ProductNotFoundException.class,
                () -> productController.getProductById(productId)
        );
    }

    @Test
    public void Test_GetProductById_WhenServiceThrowsException() throws ProductNotFoundException {
        //Arrange
        when(productService.getProductById(any(Long.class))).thenThrow(
                new ProductNotFoundException("Something went wrong")
        );

        //Act and Assert
        assertThrows(
                ProductNotFoundException.class,
                () -> productController.getProductById(100L)
        );
    }

    @Test
    public void Test_GetProductById_WithExpectedArguments_RunSuccessfully() throws ProductNotFoundException {
        //Arrange
        Long productId = 15L;
        Product product = new Product();
        product.setId(productId);
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.00);

        when(productService.getProductById(any(Long.class))).thenReturn(product);

        //Act
        productController.getProductById(productId);

        //Assert
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(idCaptor.getValue(), productId);
    }
}