package it.polito.ai.chat.controllers;

import it.polito.ai.chat.Topics;
import it.polito.ai.chat.exception.FailedToAuthenticateException;
import it.polito.ai.chat.exception.FailedToResolveUsernameException;
import it.polito.ai.chat.model.messages.ForwardedMessage;
import it.polito.ai.chat.model.messages.StoredMessage;
import it.polito.ai.chat.services.MessageService;
import it.polito.ai.chat.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * Created by france193 on 07/07/2017.
 */
@Controller
public class TrafficChatController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ProfileService profileService;

    @MessageMapping("/chat_traffic")
    @SendTo("/topic/Traffic")
    public ForwardedMessage messageForwarder(SimpMessageHeaderAccessor headerAccessor,
                                             String receivedMessage) throws FailedToAuthenticateException, FailedToResolveUsernameException {

        System.out.println("Received: " + Topics.TRAFFIC + " - " + receivedMessage);

        //create the timestamp of the message
        Date timestamp = new Date();

        //retrieve nickname from the username through the profile service
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username == null) {
            System.err.println("username in websocket session " + headerAccessor.getSessionId() + " not found");
            throw new FailedToAuthenticateException("username in websocket session " + headerAccessor.getSessionId() + " not found");
        }
        String nickname = profileService.getNickname(username);
        if (nickname == null) {
            System.err.println("nickname not resolved for username " + username);
            throw new FailedToResolveUsernameException("nickname not resolved for username " + username);
        }

        //create the message to persist
        StoredMessage messageToPersist = new StoredMessage();
        messageToPersist.setUsername(username);
        messageToPersist.setContent(receivedMessage);
        messageToPersist.setTimestamp(timestamp);
        messageService.persistMessage(Topics.TRAFFIC, messageToPersist);

        //create the message to forward
        ForwardedMessage messageToForward = new ForwardedMessage();
        messageToForward.setNickname(nickname);
        messageToForward.setTimestamp(timestamp);
        messageToForward.setContent(receivedMessage);
        messageToForward.setAvatar("hellone!!!");

        //forward the message
        return messageToForward;
    }
}
