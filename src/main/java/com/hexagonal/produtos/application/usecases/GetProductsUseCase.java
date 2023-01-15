package com.hexagonal.produtos.application.usecases;

import com.hexagonal.produtos.application.domain.ProductInterface;
import com.hexagonal.produtos.application.ports.inbound.GetProductsUseCasePort;
import com.hexagonal.produtos.application.ports.outbound.GetProductsAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProductsUseCase implements GetProductsUseCasePort {

    private final GetProductsAdapterPort getProductsAdapterPort;

    public GetProductsUseCase(GetProductsAdapterPort getProductsAdapterPort) {
        this.getProductsAdapterPort = getProductsAdapterPort;
    }

    @Override
    public List<ProductInterface> get() {
        return getProductsAdapterPort.get();
    }
}
