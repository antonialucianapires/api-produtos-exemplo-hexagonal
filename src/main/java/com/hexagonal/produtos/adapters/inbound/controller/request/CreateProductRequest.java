package com.hexagonal.produtos.adapters.inbound.controller.request;

import com.hexagonal.produtos.application.domain.Product;
import com.hexagonal.produtos.application.domain.ProductInterface;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProductRequest {

    @NotNull(message = "Name is mandatory.")
    private String name;
    @NotBlank(message = "Status is mandatory.")
    private String status;
    @NotNull(message = "Price is mandatory.")
    private Double price;

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductInterface toProductDomain() {
        var domain = new Product();
        domain.setName(this.name);
        domain.setStatus(this.status);
        domain.setPrice(this.price);
        return domain;

    }
}
