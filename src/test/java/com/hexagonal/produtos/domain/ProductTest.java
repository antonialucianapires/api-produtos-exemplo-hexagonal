package com.hexagonal.produtos.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static com.hexagonal.produtos.domain.ProductStatus.DISABLED;
import static com.hexagonal.produtos.domain.ProductStatus.ENABLED;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class ProductTest {

    private ProductInterface product;

    @BeforeEach
    void setup() {
        product = new Product();
    }

    @Test
    void getId() {
        assertNotNull(product.getId());
    }

    @Test
    void getName() {
        product.setName("Mousepad");
        assertEquals("Mousepad", product.getName());
    }

    @Test
    void getPrice() {
        product.setPrice(222.0);
        assertEquals(222.0, product.getPrice());
    }

    @Test
    void getStatus() {
        assertEquals(DISABLED.toString(), product.getStatus());
    }

    @Test
    void isValid() {
        product.setPrice(222.0);
        product.setName("Mousepad");
        assertTrue(product.isValid());

        product.setStatus(null);
        assertTrue(product.isValid());

        product.setStatus(" ");
        assertTrue(product.isValid());

        product.setStatus("");
        assertTrue(product.isValid());
    }

    @Test
    void isNotValidBecauseTheStatusIsDifferentFromEnabledAndDisabled() {
        product.setPrice(222.0);
        product.setName("Mousepad");
        product.setStatus("active");
        assertFalse(product.isValid());
    }

    @Test
    void isNotValidBecauseThePriceIsLessThanZero() {
        product.setPrice(-10.5);
        product.setName("Mousepad");
        assertFalse(product.isValid());
    }

    @Test
    void isNotValidBecauseTheIdIsNotInTheExpectedPattern() {
        product.setPrice(222.0);
        product.setName("Mousepad");
        product.setId("123");
        assertFalse(product.isValid());
    }

    @Test
    void enable() {
        product.setPrice(222.0);
        product.setName("Mousepad");

        product.enable();
        assertEquals(ENABLED.toString(), product.getStatus());
    }

    @Test
    void notEnabledBecauseThePriceIsInvalid() {
        product.setPrice(-2.0);
        product.setName("Mousepad");
        assertThrows(RuntimeException.class, () -> product.enable());
    }

    @Test
    void disable() {
        product.setPrice(0.0);
        product.setName("Mousepad");

        product.disable();
        assertEquals(DISABLED.toString(), product.getStatus());
    }

    @Test
    void notDisabledBecauseThePriceIsInvalid() {
        product.setPrice(2.0);
        product.setName("Mousepad");
        assertThrows(RuntimeException.class, () -> product.disable());
    }
}