package com.hexagonal.produtos.adapters.inbound.controller;

import com.hexagonal.produtos.adapters.inbound.controller.request.CreateProductRequest;
import com.hexagonal.produtos.application.domain.Product;
import com.hexagonal.produtos.application.ports.inbound.CreateProductUseCasePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.hexagonal.produtos.application.domain.ProductStatus.ENABLED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateProductUseCasePort createProductUseCasePort;

    @Test
    void createProductWithSucess() throws Exception {

        var product = new Product();
        product.setName("Mousepad");
        product.setPrice(222.50);
        product.setStatus(ENABLED.name());

        when(createProductUseCasePort.execute(any())).thenReturn(product);

        var request = new CreateProductRequest();
        request.setName("Mousepad");
        request.setPrice(222.50);
        request.setStatus(ENABLED.name());

        mockMvc.perform(post("/v1/products")
                .contentType("application/json")
                .content("{\"name\": \""+request.getName()+"\", \"price\": "+ request.getPrice() + ", \"status\": \""+ request.getStatus() + "\"}"))
                .andExpect(status().isOk());

    }
}