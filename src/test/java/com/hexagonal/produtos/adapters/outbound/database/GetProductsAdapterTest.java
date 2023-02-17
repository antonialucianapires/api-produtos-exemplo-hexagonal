package com.hexagonal.produtos.adapters.outbound.database;

import com.hexagonal.produtos.adapters.outbound.database.entities.ProductEntity;
import com.hexagonal.produtos.adapters.outbound.database.repository.ProductRepository;
import com.hexagonal.produtos.application.domain.ProductInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.hexagonal.produtos.application.domain.ProductStatus.ENABLED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GetProductsAdapterTest {

    @InjectMocks
    private GetProductsAdapter getProductsAdapter;

    @Mock
    private ProductRepository productRepository;

    @Test
    void get() {

        var productEntity = new ProductEntity("1234", "mouse", ENABLED.name(), 120.0);

        var entities = List.of(productEntity);

        when(productRepository.findAll()).thenReturn(entities);

        var products = getProductsAdapter.get();

        assertEquals(1, products.size());
        assertInstanceOf(ProductInterface.class, products.get(0));

    }
}