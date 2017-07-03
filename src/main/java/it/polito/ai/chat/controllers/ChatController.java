package it.polito.ai.chat.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import it.polito.ai.chat.exception.FailedToAuthenticateException;
import it.polito.ai.chat.exception.FailedToResolveUsernameException;
import it.polito.ai.chat.model.messages.ForwardedMessage;
import it.polito.ai.chat.model.messages.ReceivedMessage;
import it.polito.ai.chat.model.messages.StoredMessage;
import it.polito.ai.chat.services.MessageService;
import it.polito.ai.chat.services.ProfileService;

@Controller
public class ChatController {
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private ProfileService profileService;
	
	
	@MessageMapping("/topic/{topicId}")
	@SendTo("/topic/{topicId}")
	public ForwardedMessage messageForwarder(
			SimpMessageHeaderAccessor headerAccessor,
			@DestinationVariable String topicId,
			ReceivedMessage receivedMessage) throws FailedToAuthenticateException, FailedToResolveUsernameException{
		
		//TODO: check id the topicId is a valid one!
		
		//create the timestamp of the message
		Date timestamp = new Date();
		
		//retrieve nickname from the username through the profile service
		String username = (String) headerAccessor.getSessionAttributes().get("username");
		if(username == null){
			//TODO
			System.err.println("username in websocket session "+headerAccessor.getSessionId()+" not found");
			throw new FailedToAuthenticateException("username in websocket session "+headerAccessor.getSessionId()+" not found");
		}
		String nickname = profileService.getNickname(username);
		if(nickname == null){
			//TODO
			System.err.println("nickname not resolved for username "+username);
			throw new FailedToResolveUsernameException("nickname not resolved for username "+username);
		}
		
		//extract the information from the received message
		String messageContent = receivedMessage.getContent();
		
		//create the message to persist
		StoredMessage messageToPersist = new StoredMessage();
		messageToPersist.setUsername(username);
		messageToPersist.setContent(messageContent);
		messageToPersist.setTimestamp(timestamp);
		messageService.persistMessage(topicId, messageToPersist);
		
		//create the message to forward
		ForwardedMessage messageToForward = new ForwardedMessage();
		messageToForward.setNickname(nickname);
		messageToForward.setTimestamp(timestamp);
		messageToForward.setContent(messageContent);
		messageToForward.setAvatar("hellone!!!");
		
		//forward the message
		return messageToForward;
	}
	
}
