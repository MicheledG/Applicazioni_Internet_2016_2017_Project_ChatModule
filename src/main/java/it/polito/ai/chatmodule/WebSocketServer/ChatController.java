package it.polito.ai.chatmodule.WebSocketServer;

import it.polito.ai.chatmodule.model.messages.ForwardedMessage;
import it.polito.ai.chatmodule.model.messages.TopicIds;
import it.polito.ai.chatmodule.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class ChatController {

	@Autowired
	private TopicIds topicIds;

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MessageService messageService;
		
	@RequestMapping(value="/chat", method= RequestMethod.GET)
	public String showRequestedChat(@RequestParam(required=true) String topic, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		String nickname = loginService.getNickname(email);		
		model.addAttribute("nickname", nickname);
		model.addAttribute("chatName", topic);
		
		List<ForwardedMessage> messages = messageService.getLastMessages(topic, 10);
		
		model.addAttribute("messages", messages);

		return "chat";
	}
	
	@MessageMapping("/topic/{topicId}")
	@SendTo("/topic/{topicId}")
	public ForwardedMessage messageForwarder(SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String topicId, ReceivedMessage receivedMessage) throws Exception{
		
		if(!topicIds.getTopicIds().contains(topicId))
			return null;
		
		Authentication auth = (Authentication) headerAccessor.getHeader("simpUser");
		String email = auth.getName();
		String senderNickname = loginService.getNickname(email);	
		
		ForwardedMessage messageToForward = new ForwardedMessage();
		messageToForward.setContent(receivedMessage.getContent());
		messageToForward.setTimestamp(new Date());
		messageToForward.setNickname(senderNickname);
		messageToForward.setAvatar("hellone!!!");
		
		messageService.persistMessage(topicId, messageToForward);
		
		return messageToForward;
	}
	
}
