package it.polito.ai.chatmodule.model.messages;

public class ReceivedMessage {

	/*
	 * this is what the client application sends to the backend (MessageController)
	 * it's up to the controller adding all the information needed to create the message to persist on the db and to forward to the user
	 * all the task of the controller can be reached using the services offered in the package it.polito.ai.services (MessageService, ProfileService)
	 */

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
