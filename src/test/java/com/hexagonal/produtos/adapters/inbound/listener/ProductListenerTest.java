package com.hexagonal.produtos.adapters.inbound.listener;

import com.amazon.sqs.javamessaging.message.SQSTextMessage;
import com.hexagonal.produtos.application.domain.ProductInterface;
import com.hexagonal.produtos.application.ports.inbound.CreateProductUseCasePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.jms.JMSException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ProductListenerTest {

    @InjectMocks
    private ProductListener productListener;

    @Mock
    private CreateProductUseCasePort createProductUseCasePort;

    @Test
    void consumer() throws JMSException {

        var message = new SQSTextMessage();
        message.setText("{ \"name\": \"mousepad\", \"status\": \"ENABLED\", \"price\": 222.50 }");

        productListener.consumer(message);

        verify(createProductUseCasePort, only()).execute(any(ProductInterface.class));
    }
}