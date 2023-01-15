package com.hexagonal.produtos.adapters.inbound.controller.response;

import com.hexagonal.produtos.domain.ProductInterface;

public class ProductResponse {

    private String id;
    private String name;
    private String status;
    private Double price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
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

    public static ProductResponse fromDomain(ProductInterface domain) {
        var response = new ProductResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setStatus(domain.getStatus());
        response.setPrice(domain.getPrice());

        return response;
    }


}
