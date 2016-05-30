package com.techfocus.todo.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.techfocus.todo.AuthenticationFilter;
import com.techfocus.todo.model.Account;
import com.techfocus.todo.pojo.OutboundMessage;
import com.techfocus.todo.repository.AccountRepository;

@Service
public class NotificationService {

	final Logger logger = Logger.getLogger(NotificationService.class);
	
	
	@Resource
	private AccountRepository accountRepository;
	
	@Resource
	private SimpMessagingTemplate template;
	
	
	public void notifyAdmin(UserDetails userDetails, String message){
		Account account = accountRepository.findByUsername(userDetails.getUsername());
		if(account.getAdmin()!=null){
			logger.info("sending noticiation to topic "+account.getAdmin().getUsername());
			OutboundMessage outbound = new OutboundMessage(message);
			template.convertAndSend("/topic/"+account.getAdmin().getUsername(), outbound);
		}
		
	}
}
