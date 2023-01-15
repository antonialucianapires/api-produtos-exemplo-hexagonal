package com.hexagonal.produtos.adapters.outbound.database.repository;

import com.hexagonal.produtos.adapters.outbound.database.entities.ProductEntity;
import com.hexagonal.produtos.application.domain.ProductInterface;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
