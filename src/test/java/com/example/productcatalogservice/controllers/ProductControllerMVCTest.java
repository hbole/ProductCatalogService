package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dto.ProductDTO;
import com.example.productcatalogservice.dtomappers.ProductDTOMapper;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IProductService productService;

    @Test
    public void Test_GetAllProducts_RunSuccessfully() throws Exception {
        Product product = new Product();
        product.setId(7L);
        product.setTitle("MacBookPro");

        Product product2 = new Product();
        product2.setId(12L);
        product2.setTitle("Iphone");

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);

        String output = objectMapper.writeValueAsString(productList);

        when(productService.getProducts())
                .thenReturn(productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(output))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(7))
                .andExpect(jsonPath("$[0].title").value("MacBookPro"));
    }

    @Test
    public void Test_AddProduct_RunSuccessfully() throws Exception {
        //Arrange
        Long productId = 101L;
        String productTitle = "Test Product";
        Product product = new Product();
        product.setId(productId);
        product.setTitle(productTitle);

        ProductDTO productDTO = ProductDTOMapper.toDTO(product);

        when(productService.addProduct(any(Product.class))).thenReturn(product);

        //Act
        String productDTOString = objectMapper.writeValueAsString(productDTO);
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productDTOString)
                )
                .andExpect(status().isCreated())
                .andExpect(content().string(productDTOString))
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.title").value(productTitle));
    }
}
