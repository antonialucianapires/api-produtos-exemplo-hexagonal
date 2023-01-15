package com.hexagonal.produtos.application.ports.outbound;

import com.hexagonal.produtos.application.domain.ProductInterface;

public interface SaveProductAdapterPort {
    ProductInterface save(ProductInterface product);
}
