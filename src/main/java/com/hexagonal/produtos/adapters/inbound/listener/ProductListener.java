package com.hexagonal.produtos.adapters.inbound.listener;

import com.google.gson.Gson;
import com.hexagonal.produtos.adapters.inbound.listener.events.ProductReceivedEvent;
import com.hexagonal.produtos.application.ports.inbound.CreateProductUseCasePort;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class ProductListener {

    private final CreateProductUseCasePort createProductUseCasePort;

    public ProductListener(CreateProductUseCasePort createProductUseCasePort) {
        this.createProductUseCasePort = createProductUseCasePort;
    }

    @JmsListener(destination = "ProducReceivedQueue")
    public void consumer(TextMessage textMessage) throws JMSException {

        var gsonConverter = new Gson();
        var productEvent = gsonConverter.fromJson(textMessage.getText(), ProductReceivedEvent.class);

        createProductUseCasePort.execute(productEvent.toProductDomain());

    }
}
