package com.hexagonal.produtos.application.ports.outbound;

import com.hexagonal.produtos.application.domain.ProductInterface;

import java.util.List;

public interface GetProductsAdapterPort {
    List<ProductInterface> get();
}
