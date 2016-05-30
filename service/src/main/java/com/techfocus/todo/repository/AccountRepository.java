package com.techfocus.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techfocus.todo.model.Account;

public interface AccountRepository extends MongoRepository<Account, String>{

	public Account findByUsername(String username);
}
