package com.ecommerce.user.repository;

import com.ecommerce.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	void deleteByUsername(String username);
    User findByUsername(String username);
}
