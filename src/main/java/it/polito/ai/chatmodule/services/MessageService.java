package it.polito.ai.chatmodule.services;

import it.polito.ai.chatmodule.model.messages.ForwardedMessage;

public interface MessageService {

    void persistMessage(String topic, ForwardedMessage message);

}
