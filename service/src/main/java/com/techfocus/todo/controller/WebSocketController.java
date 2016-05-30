package com.techfocus.todo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.techfocus.todo.pojo.InboundMessage;
import com.techfocus.todo.pojo.OutboundMessage;

@Controller
public class WebSocketController {

	 @MessageMapping("/socket")
	    @SendTo("/topic/todo")
	    public OutboundMessage greeting(InboundMessage message) throws Exception {
	        Thread.sleep(3000); // simulated delay
	        return new OutboundMessage("Boardcast from /topic/todo :" + message.getContent());
	    }
	 
	 @MessageMapping("/admin")
	    @SendTo("/topic/admin")
	    public OutboundMessage sendWeather(InboundMessage message) throws Exception {
	        Thread.sleep(3000); // simulated delay
	        return new OutboundMessage("Boardcast from /topic/admin :" + message.getContent());
	    }
	 
	 
}
