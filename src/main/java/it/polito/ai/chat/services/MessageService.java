package it.polito.ai.chat.services;

import it.polito.ai.chat.exception.UnknownTopic;
import it.polito.ai.chat.model.messages.MessageResource;
import it.polito.ai.chat.model.messages.StoredMessage;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface MessageService {

    void persistMessage(String topic, StoredMessage messageToPersist);

    String createCountMessages(String topicName, Integer count) throws UnknownTopic;

    Object getTopicMessages(String topicName, PageRequest pageRequest);

    List<MessageResource> getTopicMessagesforHistory(String topicName, PageRequest pageRequest);

	String getNickname(String username);
}
