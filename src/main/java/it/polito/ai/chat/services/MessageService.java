package it.polito.ai.chat.services;

import it.polito.ai.chat.model.messages.ForwardedMessage;

public interface MessageService {

    void persistMessage(String topic, ForwardedMessage message);

}
