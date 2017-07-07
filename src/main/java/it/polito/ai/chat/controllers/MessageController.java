package it.polito.ai.chat.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.ai.chat.Topics;
import it.polito.ai.chat.exception.CustomNotFoundException;
import it.polito.ai.chat.exception.UnknownTopic;
import it.polito.ai.chat.model.messages.MessageResource;
import it.polito.ai.chat.model.messages.TopicResource;
import it.polito.ai.chat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by france193 on 24/06/2017.
 */
@RestController
public class MessageController {
    @Autowired
    MessageService messageService;

    /**
     * DEBUG PURPOSE, create count message on MongoDB
     *
     * @param topicName
     * @param count
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/topics/{topicName}/messages", method = RequestMethod.POST)
    public String setTopicMessages(@PathVariable("topicName") String topicName,
                                   @RequestParam(value = "count", defaultValue = "10", required = false) Integer count) throws JsonProcessingException, UnknownTopic {
        return messageService.createCountMessages(topicName, count);
    }

    /**
     * Retrieve all possible topics
     *
     * @return
     */
    @RequestMapping(value = "/topics", method = RequestMethod.GET, produces = "application/json")
    public List<TopicResource> getTopics() {
        List<String> topicNames = new ArrayList<>();
        topicNames.add(Topics.BIKE_TRIP);
        topicNames.add(Topics.BUS_METRO);
        topicNames.add(Topics.TRAFFIC);

        List<TopicResource> resources = new ArrayList<>();
        for (String name : topicNames) {
            TopicResource tr = new TopicResource(name);
            Link link = linkTo(MessageController.class).slash("topics").slash(name).slash("messages").withRel("messages");
            Link link3 = linkTo(MessageController.class).slash("topics").slash(name).withRel("topic").withSelfRel();
            tr.add(link);
            tr.add(link3);
            resources.add(tr);
        }

        return resources;
    }

    /**
     * Return count messages for a specific topic (endpoint for client)
     *
     * @param topicName
     * @param count
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/topics/{topicName}/messages", method = RequestMethod.GET)
    public String getTopicMessages(@PathVariable("topicName") String topicName,
                                   @RequestParam(value = "count", defaultValue = "10", required = false) Integer count) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        PageRequest pageRequest = new PageRequest(0,
                count,
                new Sort(new Sort.Order(Sort.Direction.DESC, "timestamp")));

        return mapper.writeValueAsString(messageService.getTopicMessages(topicName, pageRequest));
    }


    /**
     * Return count messages for a specific topic for history with more info (REST services)
     *
     * @param topicName
     * @param count
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/topics/{topicName}/history", method = RequestMethod.GET)
    public String getTopicMessagesHistory(@PathVariable("topicName") String topicName,
                                          @RequestParam(value = "count", defaultValue = "10", required = false) Integer count) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        PageRequest pageRequest = new PageRequest(0,
                count,
                new Sort(new Sort.Order(Sort.Direction.DESC, "timestamp")));

        List<MessageResource> topicMessages = messageService.getTopicMessagesforHistory(topicName, pageRequest);

        return mapper.writeValueAsString(topicMessages);
    }

    /**
     * Get info for topic (REST services)
     *
     * @param topicName
     * @return
     */
    @RequestMapping(value = "/topics/{topicName}")
    public TopicResource getTopic(@PathVariable("topicName") String topicName) {
        TopicResource topic;
        Link link;

        switch (topicName) {
            case Topics.BUS_METRO:
                topic = new TopicResource(topicName);
                link = linkTo(MessageController.class).slash("topics").slash(topicName).slash("messages").withRel("messages");
                topic.add(link);
                return topic;

            case Topics.TRAFFIC:
                topic = new TopicResource(topicName);
                link = linkTo(MessageController.class).slash("topics").slash(topicName).slash("messages").withRel("messages");
                topic.add(link);
                return topic;

            case Topics.BIKE_TRIP:
                topic = new TopicResource(topicName);
                link = linkTo(MessageController.class).slash("topics").slash(topicName).slash("messages").withRel("messages");
                topic.add(link);
                return topic;

            default:
                throw new CustomNotFoundException("Topic " + topicName + " doesn't exist");
        }
    }
}