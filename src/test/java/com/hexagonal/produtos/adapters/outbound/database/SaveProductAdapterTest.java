package com.hexagonal.produtos.adapters.outbound.database;

import com.hexagonal.produtos.adapters.outbound.database.entities.ProductEntity;
import com.hexagonal.produtos.adapters.outbound.database.repository.ProductRepository;
import com.hexagonal.produtos.application.domain.Product;
import com.hexagonal.produtos.application.domain.ProductInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class SaveProductAdapterTest {

    @InjectMocks
    private SaveProductAdapter saveProductAdapter;

    @Mock
    private ProductRepository productRepository;

    @Test
    void saveProduct() {

        ProductInterface product = new Product();
        product.setName("Mousepad");
        product.setPrice(250.00);

        saveProductAdapter.save(product);

        var productCaptor = ArgumentCaptor.forClass(ProductEntity.class);

        verify(productRepository, only()).save(productCaptor.capture());

        var productCaptorValue = productCaptor.getValue();
        assertEquals(product.getName(), productCaptorValue.getName());
        assertEquals(product.getStatus(), productCaptorValue.getStatus());
        assertEquals(product.getPrice(), productCaptorValue.getPrice());
        assertNotNull(productCaptorValue.getCreatedAt());
        assertNotNull(productCaptorValue.getId());

    }
}