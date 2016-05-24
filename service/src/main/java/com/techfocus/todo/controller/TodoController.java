package com.techfocus.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techfocus.todo.model.Todo;
import com.techfocus.todo.pojo.InboundMessage;
import com.techfocus.todo.pojo.OutboundMessage;
import com.techfocus.todo.repository.TodoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@RequestMapping(value= "/todo", method = RequestMethod.GET)
	public List<Todo> findAll() throws Exception{
	
		return todoRepository.findAll();
	}
	
	@RequestMapping(value= "/todo", method = RequestMethod.POST)
	public Todo create(@RequestBody Todo todo){
		return todoRepository.insert(todo);
	}
	
	@RequestMapping(value= "/todo/{id}", method = RequestMethod.PUT)
	public Todo update(@RequestBody Todo todo){
		return todoRepository.save(todo);
	}
	
	@RequestMapping(value= "/todo/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id){
		 todoRepository.delete(id);
	}
	
	

}
