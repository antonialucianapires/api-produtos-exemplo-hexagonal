package com.hexagonal.produtos.domain;

import java.util.UUID;

import static com.hexagonal.produtos.domain.ProductStatus.DISABLED;
import static com.hexagonal.produtos.domain.ProductStatus.ENABLED;
import static java.util.Objects.isNull;

public class Product implements ProductInterface {
    private String id;
    private String name;
    private Double price;
    private String status;

    public Product() {
        this.id = UUID.randomUUID().toString();
        this.status = DISABLED.name();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Boolean isValid() {

        try {

            if (isNull(this.status) || this.status.isEmpty() || this.status.isBlank()) {
                this.status = DISABLED.name();
            }

            if (!this.status.equals(DISABLED.name()) && !this.status.equals(ENABLED.name())) {
                throw new RuntimeException("the status must be enabled or disabled");
            }

            if (this.price < 0) {
                throw new RuntimeException("the price must be greater or equal zero");
            }

            if (!this.id.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                throw new RuntimeException("the id must be in the uuid pattern");
            }

        } catch (RuntimeException runtimeException) {
            return false;
        }

        return true;
    }

    @Override
    public void enable() {
        if (this.price < 0) {
            throw new RuntimeException("the price must be greater than zero to enable the product");
        }

        this.status = ENABLED.name();
    }

    @Override
    public void disable() {
        if (this.price > 0) {
            throw new RuntimeException("the price must be zero in order to have the product disabled");
        }

        this.status = DISABLED.name();
    }
}
