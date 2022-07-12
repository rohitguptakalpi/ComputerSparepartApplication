package com.java.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByProductId(long id);

}
