package com.hexagonal.produtos.application.ports.inbound;

import com.hexagonal.produtos.application.domain.ProductInterface;

public interface CreateProductUseCasePort {
    ProductInterface execute(ProductInterface productInterface);
}
