package com.hexagonal.produtos.application.usecases;

import com.hexagonal.produtos.application.domain.Product;
import com.hexagonal.produtos.application.domain.ProductInterface;
import com.hexagonal.produtos.application.ports.outbound.GetProductsAdapterPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetProductsUseCaseTest {

    @InjectMocks
    private GetProductsUseCase getProductsUseCase;

    @Mock
    private GetProductsAdapterPort getProductsAdapterPort;

    @Test
    void get() {

        ProductInterface product = new Product();
        product.setName("Mousepad");
        product.setPrice(250.00);

        when(getProductsAdapterPort.get()).thenReturn(List.of(product));

        var products = getProductsUseCase.get();

        assertTrue(products.contains(product));
    }
}