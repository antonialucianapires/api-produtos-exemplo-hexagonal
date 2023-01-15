package com.hexagonal.produtos.domain;

public interface ProductInterface {

    String getId();
    void setId(String  id);
    String getName();
    void setName(String name);
    Double getPrice();
    void setPrice(Double price);
    String getStatus();
    void setStatus(String status);

    Boolean isValid();
    void enable();
    void disable();
}
