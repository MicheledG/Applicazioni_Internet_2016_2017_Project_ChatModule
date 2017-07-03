package it.polito.ai.chat.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import it.polito.ai.chat.model.messages.ForwardedMessage;
import it.polito.ai.chat.model.messages.ReceivedMessage;
import it.polito.ai.chat.model.messages.StoredMessage;
import it.polito.ai.chat.services.MessageService;

@Controller
public class ChatController {
	
	@Autowired
	private MessageService messageService;
	
	@MessageMapping("/topic/{topicId}")
	@SendTo("/topic/{topicId}")
	public ForwardedMessage messageForwarder(SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String topicId, ReceivedMessage receivedMessage) throws Exception{
		
		//TODO: check id the topicId is a valid one!
		
		//TODO: retrieve nickname from the username through the profile service (todo)
		
		//TODO: create the message to persist
		StoredMessage messageToPersist = new StoredMessage();
		messageService.persistMessage(topicId, messageToPersist);
		
		//TODO: create the message to forward
		ForwardedMessage messageToForward = new ForwardedMessage();
		messageToForward.setContent(receivedMessage.getContent());
		messageToForward.setTimestamp(new Date());
		messageToForward.setAvatar("hellone!!!");
		
		//forward the message
		return messageToForward;
	}
	
}
