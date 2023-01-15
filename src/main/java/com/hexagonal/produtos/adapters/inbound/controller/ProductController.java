package com.hexagonal.produtos.adapters.inbound.controller;

import com.hexagonal.produtos.adapters.inbound.controller.request.CreateProductRequest;
import com.hexagonal.produtos.adapters.inbound.controller.response.ProductResponse;
import com.hexagonal.produtos.application.ports.inbound.CreateProductUseCasePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final CreateProductUseCasePort createProductUseCasePort;

    public ProductController(CreateProductUseCasePort createProductUseCasePort) {
        this.createProductUseCasePort = createProductUseCasePort;
    }

    @PostMapping()
    public ResponseEntity<Object> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(ProductResponse.fromDomain(createProductUseCasePort.execute(createProductRequest.toProductDomain())));
    }
}
