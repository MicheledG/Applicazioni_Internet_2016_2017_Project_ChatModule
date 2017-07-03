package it.polito.ai.chat.services;

import it.polito.ai.chat.exception.UnknownTopic;
import it.polito.ai.chat.model.messages.StoredMessage;
import org.springframework.data.domain.PageRequest;

public interface MessageService {

    void persistMessage(String topic, StoredMessage messageToPersist);

    String createCountMessages(String topicName, Integer count) throws UnknownTopic;

    Object getTopicMessages(String topicName, PageRequest pageRequest);
}
