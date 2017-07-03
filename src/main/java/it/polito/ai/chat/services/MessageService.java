package it.polito.ai.chat.services;

import it.polito.ai.chat.model.messages.StoredMessage;

public interface MessageService {

    void persistMessage(String topic, StoredMessage messageToPersist);

}
