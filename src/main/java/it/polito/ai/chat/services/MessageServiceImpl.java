package it.polito.ai.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import it.polito.ai.chat.model.messages.ForwardedMessage;
import it.polito.ai.chat.model.messages.Message;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MongoOperations mongoOperation;
    @Autowired
    private UserService userService;

    @Override
    public void persistMessage(String topic, ForwardedMessage message) {
        Message mongoMessage = new Message();
        String user = userService.getEmail(message.getNickname());
        mongoMessage.setUser(user);
        mongoMessage.setTimestamp(message.getTimestamp());
        mongoMessage.setContent(message.getContent());

        // persist message on the db on the collection corresponding to the
        // topic
        mongoOperation.insert(mongoMessage, topic);
    }

}
