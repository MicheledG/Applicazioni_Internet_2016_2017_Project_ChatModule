package it.polito.ai.chat.services;

import it.polito.ai.chat.Topics;
import it.polito.ai.chat.controllers.MessageController;
import it.polito.ai.chat.exception.CustomNotFoundException;
import it.polito.ai.chat.exception.FailedToResolveUsernameException;
import it.polito.ai.chat.exception.UnknownTopic;
import it.polito.ai.chat.model.messages.*;
import it.polito.ai.chat.repositories.messages.BikeTripMessageRepository;
import it.polito.ai.chat.repositories.messages.BusMetroMessageRepository;
import it.polito.ai.chat.repositories.messages.TrafficMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private BikeTripMessageRepository bikeTripMessageRepository;
    @Autowired
    private TrafficMessageRepository trafficMessageRepository;
    @Autowired
    private BusMetroMessageRepository busMetroMessageRepository;
    @Autowired
    MessageAssembler messageAssembler;
    
    @Autowired
    private ProfileService profileService;

    /**
     * Persist Message on MongoDB
     *
     * @param topicName
     * @param message
     */
    @Override
    public void persistMessage(String topicName, StoredMessage message) {
        // select the right repository where to store the message depending on the topic
        switch (topicName) {
            case Topics.BUS_METRO:
                busMetroMessageRepository.save(new BusMetroMessage(message.getTimestamp(), message.getUsername(), message.getContent()));
                break;

            case Topics.TRAFFIC:
                trafficMessageRepository.save(new TrafficMessage(message.getTimestamp(), message.getUsername(), message.getContent()));
                break;

            case Topics.BIKE_TRIP:
                bikeTripMessageRepository.save(new BikeTripMessage(message.getTimestamp(), message.getUsername(), message.getContent()));
                break;
            default:
                throw new UnknownTopic("Cannot create messages about this topic: " + topicName);
        }
    }

    /**
     * For DEBUG Purpose, create count messages for selected topic on MongoDB
     *
     * @param topicName
     * @param count
     * @return
     * @throws UnknownTopic
     */
    @Override
    public String createCountMessages(String topicName, Integer count) throws UnknownTopic {
        switch (topicName) {
            case Topics.BUS_METRO:
                for (int i = 0; i < count; i++) {
                    busMetroMessageRepository.save(new BusMetroMessage(new Date(), "thomasvitale93@gmail.com", "Bus&Metro " + i));
                }
                return "Created " + count + " messages about topic: " + Topics.BUS_METRO;

            case Topics.TRAFFIC:
                for (int i = 0; i < count; i++) {
                    trafficMessageRepository.save(new TrafficMessage(new Date(), "thomasvitale93@gmail.com", "Traffic " + i));
                }
                return "Created " + count + " messages about topic: " + Topics.TRAFFIC;

            case Topics.BIKE_TRIP:
                for (int i = 0; i < count; i++) {
                    bikeTripMessageRepository.save(new BikeTripMessage(new Date(), "thomasvitale93@gmail.com", "BikeTrip " + i));
                }
                return "Created " + count + " messages about topic: " + Topics.BIKE_TRIP;

            default:
                throw new UnknownTopic("Cannot create messages about this topic: " + topicName);
        }
    }

    @Override
    public Object getTopicMessages(String topicName, PageRequest pageRequest) {
        switch (topicName) {
            case Topics.BUS_METRO:
                return busMetroMessageRepository.findAll(pageRequest).getContent();

            case Topics.TRAFFIC:
                return trafficMessageRepository.findAll(pageRequest).getContent();

            case Topics.BIKE_TRIP:
                return bikeTripMessageRepository.findAll(pageRequest).getContent();

            default:
                return new CustomNotFoundException("Topic " + topicName + " doesn't exist").getMessage();
        }
    }

    @Override
    public List<MessageResource> getTopicMessagesforHistory(String topicName, PageRequest pageRequest) {
        switch (topicName) {
            case Topics.BUS_METRO:
                return createResources(busMetroMessageRepository.findAll(pageRequest), topicName);

            case Topics.TRAFFIC:
                return createResources(trafficMessageRepository.findAll(pageRequest), topicName);

            case Topics.BIKE_TRIP:
                return createResources(bikeTripMessageRepository.findAll(pageRequest), topicName);

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
    
    @Override
    public String getNickname(String username) {
    	String nickname = profileService.getNickname(username);
        if (nickname == null) {
            System.err.println("nickname not resolved for username " + username);
            throw new FailedToResolveUsernameException("nickname not resolved for username " + username);
        }
        
        return nickname;
    }
}
