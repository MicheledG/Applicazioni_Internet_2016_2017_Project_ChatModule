package it.polito.ai.chat.model.messages;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import it.polito.ai.chat.controllers.MessageController;

@Component
public class MessageAssembler extends ResourceAssemblerSupport<Message, MessageResource> {

    public MessageAssembler() {
        super(MessageController.class, MessageResource.class);
    }

    @Override
    public MessageResource toResource(Message message) {
        if (message == null) {
            return null;
        }

        MessageResource ur = instantiateResource(message);
        ur.setId(message.getId());
        ur.setContent(message.getContent());
        ur.setTimestamp(message.getTimestamp());
        return ur;
    }

}
