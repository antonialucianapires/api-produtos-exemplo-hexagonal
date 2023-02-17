package com.hexagonal.produtos.adapters.outbound.producer;

import com.google.gson.Gson;
import com.hexagonal.produtos.application.domain.ProductInterface;
import com.hexagonal.produtos.application.ports.outbound.SendProductsForDistributionAdapterPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SendProductsForDistributionAdapter implements SendProductsForDistributionAdapterPort {

    @Value("${producers.queue.distributed-products}")
    private String queueDistributedProducts;
    private final GenericProducerSQS producer;


    public SendProductsForDistributionAdapter(GenericProducerSQS producer) {
        this.producer = producer;
    }

    @Override
    public void send(List<ProductInterface> products) {

        var converter = new Gson();
        var productsJson = products.stream().map(converter::toJson).collect(Collectors.toList());
        producer.sentToQueue(productsJson, queueDistributedProducts);

    }
}
