package com.techfocus.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techfocus.todo.model.Todo;

public interface TodoRepository extends MongoRepository<Todo, String> {

}
