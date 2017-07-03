package it.polito.ai.chat.model.messages;

import org.springframework.hateoas.ResourceSupport;

//this class is the Topic REST Resource
public class TopicResource extends ResourceSupport {

    private String topicId;

    public TopicResource(String topicName) {
        this.topicId = topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

}
