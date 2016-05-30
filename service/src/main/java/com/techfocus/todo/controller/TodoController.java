package com.techfocus.todo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techfocus.todo.model.Todo;
import com.techfocus.todo.repository.TodoRepository;
import com.techfocus.todo.service.NotificationService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class TodoController {
	
	@Resource
	private TodoRepository todoRepository;
	
	@Resource
	private SimpMessagingTemplate template;
	
	@Resource
	private NotificationService notificationService;
	
	@RequestMapping(value= "/todo", method = RequestMethod.GET)
	public List<Todo> findAll() throws Exception{
	
		return todoRepository.findAll();
	}
	
	@RequestMapping(value= "/todo", method = RequestMethod.POST)
	public Todo create(@RequestBody Todo todo, @AuthenticationPrincipal UserDetails userDetails){
		todo = todoRepository.insert(todo);
		notificationService.notifyAdmin(userDetails, "New TODO added");
		return todo;
		 
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
