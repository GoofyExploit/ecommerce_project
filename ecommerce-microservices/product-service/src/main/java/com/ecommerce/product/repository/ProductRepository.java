package com.ecommerce.product.repository;

import com.ecommerce.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
	Product findByName(String name);

	void deleteByName(String name);
}
