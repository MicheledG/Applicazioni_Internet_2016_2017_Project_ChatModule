package it.polito.ai.chat.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by france193 on 07/07/2017.
 */
@Controller
public class BasicSocketController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String prova(String ricevuto) {
        System.out.println("4 Received: " + ricevuto);
        return "ricevuto " + ricevuto;
    }
}