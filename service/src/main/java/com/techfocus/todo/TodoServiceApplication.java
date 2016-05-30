package com.techfocus.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.techfocus.todo.model.Account;
import com.techfocus.todo.repository.AccountRepository;

@SpringBootApplication

public class TodoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoServiceApplication.class, args);
	}
	
	
	 @Bean
	    CommandLineRunner init(final AccountRepository accountRepository) {
	      
	      return new CommandLineRunner() {

	        @Override
	        public void run(String... arg0) throws Exception {
	        	
	        	if(accountRepository.findAll().size()==0){
	        		String[] adminRoles ={"USER","ADMIN"};
	        		String[] userRoles = {"USER"};
	  	          	Account admin = accountRepository.save(new Account("admin1", "password",adminRoles));
	  	          	accountRepository.save(new Account("adminuser1", "password",userRoles,admin));
	  	          
	        	}
	        	
	          
	        }
	        
	      };

	    }
}

