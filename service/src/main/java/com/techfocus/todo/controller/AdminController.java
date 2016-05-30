package com.techfocus.todo.controller;

import javax.annotation.Resource;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techfocus.todo.model.Account;
import com.techfocus.todo.repository.AccountRepository;

@RestController
@RequestMapping("/api")
public class AdminController {

	@Resource
	private AccountRepository accountRepository;
	
	@RequestMapping("/info/user")
	public Account getUserDetails(@AuthenticationPrincipal UserDetails userDetails){
		
		Account account = accountRepository.findByUsername(userDetails.getUsername());
		account.setPassword(null);
		return account;
	}
}
