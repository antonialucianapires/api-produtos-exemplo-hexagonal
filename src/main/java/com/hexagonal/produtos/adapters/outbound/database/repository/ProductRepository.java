package com.hexagonal.produtos.adapters.outbound.database.repository;

import com.hexagonal.produtos.adapters.outbound.database.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
