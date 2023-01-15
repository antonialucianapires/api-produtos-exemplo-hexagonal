package com.hexagonal.produtos.application.usecases;

import com.hexagonal.produtos.application.ports.inbound.CreateProductUseCasePort;
import com.hexagonal.produtos.application.ports.outbound.SaveProductAdapterPort;
import com.hexagonal.produtos.domain.ProductInterface;
import org.springframework.stereotype.Component;

@Component
public class CreateProductUseCaseUseCase implements CreateProductUseCasePort {

    private final SaveProductAdapterPort saveProductAdapterPort;

    public CreateProductUseCaseUseCase(SaveProductAdapterPort saveProductAdapterPort) {
        this.saveProductAdapterPort = saveProductAdapterPort;
    }

    @Override
    public ProductInterface execute(ProductInterface product) {
        return saveProductAdapterPort.save(product);
    }
}
