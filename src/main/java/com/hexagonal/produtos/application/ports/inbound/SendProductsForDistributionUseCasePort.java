package com.hexagonal.produtos.application.ports.inbound;

import com.hexagonal.produtos.application.domain.ProductInterface;

import java.util.List;

public interface SendProductsForDistributionUseCasePort {
    void send(List<ProductInterface> products);
}
