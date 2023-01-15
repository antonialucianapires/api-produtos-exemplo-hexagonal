package com.hexagonal.produtos.adapters.outbound.database;

import com.hexagonal.produtos.adapters.outbound.database.entities.ProductEntity;
import com.hexagonal.produtos.adapters.outbound.database.repository.ProductRepository;
import com.hexagonal.produtos.application.ports.outbound.SaveProductAdapterPort;
import com.hexagonal.produtos.application.domain.ProductInterface;
import org.springframework.stereotype.Component;

@Component
public class SaveProductAdapter implements SaveProductAdapterPort {

    private final ProductRepository repository;

    public SaveProductAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductInterface save(ProductInterface product) {
        var entity = new ProductEntity(product.getId(),product.getName(),product.getStatus(), product.getPrice());
        repository.save(entity);
        return product;
    }
}
