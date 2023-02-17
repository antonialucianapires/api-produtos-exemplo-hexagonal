package com.hexagonal.produtos.application.usecases;

import com.hexagonal.produtos.application.domain.Product;
import com.hexagonal.produtos.application.domain.ProductInterface;
import com.hexagonal.produtos.application.ports.outbound.SaveProductAdapterPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CreateProductUseCaseUseCaseTest {

    @InjectMocks
    private CreateProductUseCaseUseCase createProductUseCaseUseCase;
    @Mock
    private SaveProductAdapterPort saveProductAdapterPort;


    @Test
    void execute() {

        ProductInterface product = new Product();
        product.setName("Mousepad");
        product.setPrice(250.00);

        when(saveProductAdapterPort.save(any())).thenReturn(new Product());

        createProductUseCaseUseCase.execute(product);

        verify(saveProductAdapterPort, only()).save(any(ProductInterface.class));
    }
}