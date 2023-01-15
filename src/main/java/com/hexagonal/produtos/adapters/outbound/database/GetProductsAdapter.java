package com.hexagonal.produtos.adapters.outbound.database;

import com.hexagonal.produtos.adapters.outbound.database.repository.ProductRepository;
import com.hexagonal.produtos.application.domain.Product;
import com.hexagonal.produtos.application.domain.ProductInterface;
import com.hexagonal.produtos.application.ports.outbound.GetProductsAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetProductsAdapter implements GetProductsAdapterPort {

    private final ProductRepository productRepository;

    public GetProductsAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductInterface> get() {
        return productRepository.findAll().stream().map(productEntity -> {
            var productDomain = new Product();
            productDomain.setName(productEntity.getName());
            productDomain.setPrice(productEntity.getPrice());
            productDomain.setStatus(productEntity.getStatus());
            productDomain.setId(productEntity.getId());
            return productDomain;
        }).collect(Collectors.toList());
    }
}
