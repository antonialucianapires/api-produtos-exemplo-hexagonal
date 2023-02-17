package com.hexagonal.produtos.application.usecases;

import com.hexagonal.produtos.application.domain.ProductInterface;
import com.hexagonal.produtos.application.ports.inbound.SendProductsForDistributionUseCasePort;
import com.hexagonal.produtos.application.ports.outbound.SendProductsForDistributionAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendProductsForDistributionUseCase implements SendProductsForDistributionUseCasePort {

    private final SendProductsForDistributionAdapterPort sendProductsForDistributionAdapterPort;

    public SendProductsForDistributionUseCase(SendProductsForDistributionAdapterPort sendProductsForDistributionAdapterPort) {
        this.sendProductsForDistributionAdapterPort = sendProductsForDistributionAdapterPort;
    }

    @Override
    public void send(List<ProductInterface> products) {
        sendProductsForDistributionAdapterPort.send(products);
    }
}
