package com.techfocus.todo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techfocus.todo.model.Account;
import com.techfocus.todo.pojo.OutboundMessage;
import com.techfocus.todo.repository.AccountRepository;

@RestController
@RequestMapping("/api")

public class AccountController {
	
	@Resource
	private AccountRepository accountRepository;
	
	@Resource
	private SimpMessagingTemplate template;
	
	@RequestMapping(value= "/user", method = RequestMethod.GET)
	public List<Account> findAll(@AuthenticationPrincipal UserDetails userDetails) throws Exception{
	
		return accountRepository.findAll();
	}
	
	@RequestMapping(value= "/user", method = RequestMethod.POST)
	public Account create(@RequestBody Account user){
		
		user = accountRepository.insert(user);
		OutboundMessage message = new OutboundMessage("New User added");
		template.convertAndSend("/topic/todo", message);
		return user;
		 
	}
	
	@RequestMapping(value= "/user/{id}", method = RequestMethod.PUT)
	public Account update(@RequestBody Account user){
		return accountRepository.save(user);
	}
	
	@RequestMapping(value= "/user/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id){
		accountRepository.delete(id);
	}
	
	

}
