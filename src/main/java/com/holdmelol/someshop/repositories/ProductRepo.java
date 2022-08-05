package com.holdmelol.someshop.repositories;

import com.holdmelol.someshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
