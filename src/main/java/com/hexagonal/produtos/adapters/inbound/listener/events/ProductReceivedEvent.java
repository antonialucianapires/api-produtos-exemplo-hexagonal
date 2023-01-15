package com.hexagonal.produtos.adapters.inbound.listener.events;

import com.hexagonal.produtos.application.domain.Product;
import com.hexagonal.produtos.application.domain.ProductInterface;

public class ProductReceivedEvent {

    private String name;
    private String status;
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

    public ProductInterface toProductDomain() {
        var domain = new Product();
        domain.setName(this.name);
        domain.setStatus(this.status);
        domain.setPrice(this.price);
        return domain;

    }
}
