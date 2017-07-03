package it.polito.ai.chat.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ai.chat.model.messages.*;
import it.polito.ai.chat.repositories.messages.BikeTripMessageRepository;
import it.polito.ai.chat.repositories.messages.BusMetroMessageRepository;
import it.polito.ai.chat.repositories.messages.TrafficMessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by france193 on 24/06/2017.
 */
@RestController
public class MessageController {
    private static final String BUS_METRO = "BusMetro";
    private static final String TRAFFIC = "Traffic";
    private static final String BIKE_TRIP = "BikeTrip";

    @Autowired
    private BusMetroMessageRepository busMetroMessageRepository;
    @Autowired
    private BikeTripMessageRepository bikeTripMessageRepository;
    @Autowired
    private TrafficMessageRepository trafficMessageRepository;
    @Autowired
    MessageAssembler messageAssembler;

    /**
     * DEBUG PURPOSE, create count message on database
     *
     * @param topicName
     * @param count
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/topics/{topicName}/messages", method = RequestMethod.POST)
    public String setTopicMessages(@PathVariable("topicName") String topicName,
                                   @RequestParam(value = "count", defaultValue = "10", required = false) Integer count) throws JsonProcessingException {
        switch (topicName) {
            case BUS_METRO:
                for (int i = 0; i < count; i++) {
                    busMetroMessageRepository.save(new BusMetroMessage(new Date(), "mail1@test.com", "Bus&Metro " + i));
                }
                return "Created " + count + " messages about topic: " + BUS_METRO;

            case TRAFFIC:
                for (int i = 0; i < count; i++) {
                    trafficMessageRepository.save(new TrafficMessage(new Date(), "mail1@test.com", "Bus&Traffic " + i));
                }
                return "Created " + count + " messages about topic: " + TRAFFIC;

            case BIKE_TRIP:
                for (int i = 0; i < count; i++) {
                    bikeTripMessageRepository.save(new BikeTripMessage(new Date(), "mail1@test.com", "BikeTrip " + i));
                }
                return "Created " + count + " messages about topic: " + BIKE_TRIP;

            default:
                return "Cannot create messages about this topic: " + topicName;
        }
    }

    /**
     * Retrieve all possible topics
     *
     * @return
     */
    @RequestMapping(value = "/topics", method = RequestMethod.GET, produces = "application/json")
    public List<TopicResource> getTopics() {
        List<String> topicNames = new ArrayList<>();
        topicNames.add(BIKE_TRIP);
        topicNames.add(BUS_METRO);
        topicNames.add(TRAFFIC);

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

        switch (topicName) {
            case BUS_METRO:
                return mapper.writeValueAsString(busMetroMessageRepository.findAll(pageRequest).getContent());

            case TRAFFIC:
                return mapper.writeValueAsString(trafficMessageRepository.findAll(pageRequest).getContent());

            case BIKE_TRIP:
                return mapper.writeValueAsString(bikeTripMessageRepository.findAll(pageRequest).getContent());

            default:
                return new CustomNotFoundException("Topic " + topicName + " doesn't exist").getMessage();
        }
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

        List<MessageResource> topicMessages;

        switch (topicName) {
            case BUS_METRO:
                topicMessages = createResources(busMetroMessageRepository.findAll(pageRequest), topicName);
                break;

            case TRAFFIC:
                topicMessages = createResources(trafficMessageRepository.findAll(pageRequest), topicName);
                break;

            case BIKE_TRIP:
                topicMessages = createResources(bikeTripMessageRepository.findAll(pageRequest), topicName);
                break;

            default:
                throw new CustomNotFoundException("Topic " + topicName + " doesn't exist");
        }
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
            case BUS_METRO:
                topic = new TopicResource(topicName);
                link = linkTo(MessageController.class).slash("topics").slash(topicName).slash("messages").withRel("messages");
                topic.add(link);
                return topic;

            case TRAFFIC:
                topic = new TopicResource(topicName);
                link = linkTo(MessageController.class).slash("topics").slash(topicName).slash("messages").withRel("messages");
                topic.add(link);
                return topic;

            case BIKE_TRIP:
                topic = new TopicResource(topicName);
                link = linkTo(MessageController.class).slash("topics").slash(topicName).slash("messages").withRel("messages");
                topic.add(link);
                return topic;

            default:
                throw new CustomNotFoundException("Topic " + topicName + " doesn't exist");
        }
    }

    private List<MessageResource> createResources(Page<?> pages, String topicName) {
        List<MessageResource> topicMessages = new ArrayList<>();
        for (Object message : pages.getContent()) {
            MessageResource resource = messageAssembler.toResource((StoredMessage) message);
            Link link = linkTo(MessageController.class).slash("topics").slash(topicName).withRel("myTopic");
            Link link2 = linkTo(MessageController.class).slash("topics").withRel("topics");
            resource.add(link);
            resource.add(link2);
            topicMessages.add(resource);
        }

        return topicMessages;
    }
}